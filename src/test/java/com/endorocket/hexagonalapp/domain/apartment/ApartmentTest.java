package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.booking.BookingAssertion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentTest {
	private static final String OWNER_ID = "1234";
	private static final String STREET = "Krakowska";
	private static final String POSTAL_CODE = "12-345";
	private static final String HOUSE_NUMBER = "1";
	private static final String APARTMENT_NUMBER = "12";
	private static final String CITY = "Wrocław";
	private static final String COUNTRY = "Poland";
	private static final String DESCRIPTION = "Nice apartment";
	private static final Map<String, Double> ROOMS_DEFINITION = Map.of("Toilet", 10.0, "Bedroom", 15.5);
	private static final String TENANT_ID = "126";
	private static final LocalDate START = LocalDate.of(2020, 3, 5);
	private static final LocalDate MIDDLE = LocalDate.of(2020, 3, 6);
	private static final LocalDate END = LocalDate.of(2020, 3, 7);
	private static final Period PERIOD = new Period(START, END);

	private final ApartmentEventsPublisher apartmentEventsPublisher = mock(ApartmentEventsPublisher.class);

	@Test
	void shouldCreateApartmentWithAllRequiredFields() {
		Apartment actual = createApartment();

		ApartmentAssertion.assertThat(actual)
			.hasOwnerIdEqualTo(OWNER_ID)
			.hasDescriptionEqualTo(DESCRIPTION)
			.hasApartmentNumber(APARTMENT_NUMBER)
			.hasAddressEqualTo(STREET, POSTAL_CODE, HOUSE_NUMBER, CITY, COUNTRY)
			.hasRoomsEqualsTo(ROOMS_DEFINITION);
	}

	@Test
	void shouldCreateBookingOnceBooked() {
		Apartment apartment = createApartment();

		Booking actual = apartment.book(TENANT_ID, PERIOD, apartmentEventsPublisher);

		BookingAssertion.assertThat(actual)
			.isApartment()
			.hasTenantIdEqualTo(TENANT_ID)
			.containsAllDays(List.of(START, MIDDLE, END));
	}

	@Test
	void shouldPublishApartmentBooked() {
		Apartment apartment = createApartment();

		apartment.book(TENANT_ID, PERIOD, apartmentEventsPublisher);

		then(apartmentEventsPublisher).should().publishApartmentBooked(any(), eq(OWNER_ID), eq(TENANT_ID), eq(new Period(START, END)));
	}

	private Apartment createApartment() {
    return apartment()
        .withOwnerId(OWNER_ID)
        .withStreet(STREET)
        .withPostalCode(POSTAL_CODE)
        .withHouseNumber(HOUSE_NUMBER)
        .withApartmentNumber(APARTMENT_NUMBER)
        .withCity(CITY)
        .withCountry(COUNTRY)
        .withDescription(DESCRIPTION)
        .withRoomsDefinition(ROOMS_DEFINITION)
        .build();
  }
}