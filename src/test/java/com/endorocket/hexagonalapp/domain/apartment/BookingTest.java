package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import static com.endorocket.hexagonalapp.domain.apartment.BookingAssertion.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class BookingTest {
	private final static String APARTMENT_ID = "1234";
	private final static String TENANT_ID = "2345";
	private final static LocalDate START = LocalDate.of(2020, 12, 1);
	private final static LocalDate MIDDLE = LocalDate.of(2020, 12, 2);
	private final static LocalDate END = LocalDate.of(2020, 12, 3);

	private final EventChannel eventChannel = mock(EventChannel.class);

	@Test
	void shouldCreateBookingForApartment() {
		Booking actual = Booking.apartment(APARTMENT_ID, TENANT_ID, new Period(START, END));

		assertThat(actual)
			.isOpen()
			.isRentalTypeOf(RentalType.APARTMENT)
			.hasRentalPlaceIdEqualTo(APARTMENT_ID)
			.hasTenantIdEqualTo(TENANT_ID)
			.containsAllDays(List.of(START, LocalDate.of(2020, 12, 2), END));
	}

	@Test
	void shouldCreateBookingForHotelRoom() {
		String hotelRoomId = "1";
		List<LocalDate> days = List.of(LocalDate.of(2021, 1, 11), LocalDate.of(2021, 1, 12));

		Booking actual = Booking.hotelRoom(hotelRoomId, TENANT_ID, days);

		assertThat(actual)
			.isOpen()
			.isRentalTypeOf(RentalType.HOTEL_ROOM)
			.hasRentalPlaceIdEqualTo(hotelRoomId)
			.hasTenantIdEqualTo(TENANT_ID)
			.containsAllDays(days);
	}

	@Test
	void shouldChangeStatusOfBookingOnceAccepted() {
		Booking booking = Booking.apartment(APARTMENT_ID, TENANT_ID, new Period(START, END));

		booking.accept(eventChannel);

		assertThat(booking).isAccepted();
	}

	@Test
	void shouldPublishBookingAcceptedOnceAccepted() {
		ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);
		Booking booking = Booking.apartment(APARTMENT_ID, TENANT_ID, new Period(START, END));
		LocalDateTime beforeNow = LocalDateTime.now().minusSeconds(1);

		booking.accept(eventChannel);
		then(eventChannel).should().publish(captor.capture());

		BookingAcceptedAssertion.assertThat(captor.getValue())
			.hasEventIdMatching(Pattern.compile("[0-9a-z\\-]{36}"))
			.hasEventCreationDateTimeBetween(beforeNow, LocalDateTime.now().plusSeconds(1))
			.isRentalTypeOf("APARTMENT")
			.hasRentalPlaceIdEqualTo(APARTMENT_ID)
			.hasTenantIdEqualTo(TENANT_ID)
			.containsAllDays(List.of(START, MIDDLE, END));
	}
}