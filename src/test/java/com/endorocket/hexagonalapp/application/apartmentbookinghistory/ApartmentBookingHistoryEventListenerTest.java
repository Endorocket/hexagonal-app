package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentBookedTestFactory;
import com.endorocket.hexagonalapp.domain.apartment.Period;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentBookingHistoryEventListenerTest {
	private static final String APARTMENT_ID = "1234";
	private static final String OWNER_ID = "5678";
	private static final String TENANT_ID = "3456";
	private static final LocalDate START = LocalDate.of(2020, 10, 11);
	private static final LocalDate END = LocalDate.of(2020, 10, 12);
	private static final Period PERIOD = new Period(START, END);
	private static final int FIRST_BOOKING = 1;
	private static final int NEXT_BOOKING = 2;

	private final ArgumentCaptor<ApartmentBookingHistory> captor = ArgumentCaptor.forClass(ApartmentBookingHistory.class);
	private final ApartmentBookingHistoryRepository repository = mock(ApartmentBookingHistoryRepository.class);
	private final ApartmentBookingHistoryEventListener eventListener = new ApartmentBookingHistoryEventListener(repository);

	@Test
	void shouldCreateApartmentBookingHistoryWhenConsumingApartmentBooked() {
		givenNotExistingApartmentBookingHistory();

		eventListener.consume(givenApartmentBooked());

		then(repository).should().save(captor.capture());
		thenApartmentBookingHistoryShouldHaveApartmentBookings(captor.getValue(), FIRST_BOOKING);
	}

	private void givenNotExistingApartmentBookingHistory() {
		given(repository.existsFor(APARTMENT_ID)).willReturn(false);
	}

	@SuppressWarnings("unchecked")
	private void thenApartmentBookingHistoryShouldHaveApartmentBookings(ApartmentBookingHistory actual, int bookingSize) {
		Assertions.assertThat(actual).extracting("bookings").satisfies(actualBookings -> {
			List<ApartmentBooking> bookings = (List<ApartmentBooking>) actualBookings;
			Assertions.assertThat(bookings)
				.hasSize(bookingSize)
				.anySatisfy(actualBooking ->
					ApartmentBookingAssertion.assertThat(actualBooking)
						.hasOwnerIdEqualTo(OWNER_ID)
						.hasTenantIdEqualTo(TENANT_ID)
						.hasBookingPeriodThatHas(START, END));
		});
	}

	@Test
	void shouldUpdateExistingApartmentBookingHistoryWhenConsumingApartmentBooked() {
		givenExistingApartmentBookingHistory();

		eventListener.consume(givenApartmentBooked());

		then(repository).should().save(captor.capture());
		thenApartmentBookingHistoryShouldHaveApartmentBookings(captor.getValue(), NEXT_BOOKING);
	}

	private void givenExistingApartmentBookingHistory() {
		given(repository.existsFor(APARTMENT_ID)).willReturn(true);
		ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistory();
		given(repository.findFor(APARTMENT_ID)).willReturn(apartmentBookingHistory);
	}

	private ApartmentBookingHistory getApartmentBookingHistory() {
		ApartmentBookingHistory apartmentBookingHistory = new ApartmentBookingHistory(APARTMENT_ID);
		apartmentBookingHistory.add(ApartmentBooking.start(
			LocalDateTime.now(), OWNER_ID, "3567", new BookingPeriod(LocalDate.now(), LocalDate.now().plusDays(1))));
		return apartmentBookingHistory;
	}

	private ApartmentBooked givenApartmentBooked() {
		return ApartmentBookedTestFactory.create(APARTMENT_ID, OWNER_ID, TENANT_ID, PERIOD);
	}

}