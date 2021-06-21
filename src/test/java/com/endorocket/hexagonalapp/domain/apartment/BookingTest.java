package com.endorocket.hexagonalapp.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.endorocket.hexagonalapp.domain.apartment.BookingAssertion.assertThat;

class BookingTest {

	@Test
	void shouldCreateBookingForApartment() {
		String apartmentId = "1234";
		String tenantId = "2345";
		LocalDate start = LocalDate.of(2020, 12, 1);
		LocalDate end = LocalDate.of(2020, 12, 3);

		Booking actual = Booking.apartment(apartmentId, tenantId, new Period(start, end));

		assertThat(actual)
			.isOpen()
			.isRentalTypeOf(RentalType.APARTMENT)
			.hasRentalPlaceIdEqualTo(apartmentId)
			.hasTenantIdEqualTo(tenantId)
			.containsAllDays(List.of(start, LocalDate.of(2020, 12, 2), end));
	}

	@Test
	void shouldCreateBookingForHotelRoom() {
		String hotelRoomId = "1";
		String tenantId = "234";
		List<LocalDate> days = List.of(LocalDate.of(2021, 1, 11), LocalDate.of(2021, 1, 12));

		Booking actual = Booking.hotelRoom(hotelRoomId, tenantId, days);

		assertThat(actual)
			.isOpen()
			.isRentalTypeOf(RentalType.HOTEL_ROOM)
			.hasRentalPlaceIdEqualTo(hotelRoomId)
			.hasTenantIdEqualTo(tenantId)
			.containsAllDays(days);
	}
}