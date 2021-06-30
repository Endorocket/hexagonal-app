package com.endorocket.hexagonalapp.domain.booking;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class BookingAssertion {
	private final Booking actual;

	private BookingAssertion(Booking actual) {
		this.actual = actual;
	}

	public static BookingAssertion assertThat(Booking actual) {
		return new BookingAssertion(actual);
	}

	public BookingAssertion isOpen() {
		return hasBookingStatusEqualTo(BookingStatus.OPEN);
	}

	public BookingAssertion isAccepted() {
		return hasBookingStatusEqualTo(BookingStatus.ACCEPTED);
	}

	public BookingAssertion isRejected() {
		return hasBookingStatusEqualTo(BookingStatus.REJECTED);
	}

	private BookingAssertion hasBookingStatusEqualTo(BookingStatus accepted) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", accepted);
		return this;
	}

	public BookingAssertion isHotelRoom() {
		return isRentalTypeOf(RentalType.HOTEL_ROOM);
	}

	public BookingAssertion isApartment() {
		return isRentalTypeOf(RentalType.APARTMENT);
	}

	private BookingAssertion isRentalTypeOf(RentalType expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", expected);
		return this;
	}

	public BookingAssertion hasRentalPlaceIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
		return this;
	}

	public BookingAssertion hasTenantIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
		return this;
	}

	public BookingAssertion containsAllDays(List<LocalDate> expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("days", expected);
		return this;
	}
}
