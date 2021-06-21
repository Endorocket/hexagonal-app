package com.endorocket.hexagonalapp.domain.apartment;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class BookingAssertion {
	private final Booking actual;

	private BookingAssertion(Booking actual) {
		this.actual = actual;
	}

	static BookingAssertion assertThat(Booking actual) {
		return new BookingAssertion(actual);
	}

	BookingAssertion isOpen() {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", BookingStatus.OPEN);
		return this;
	}

	BookingAssertion isRentalTypeOf(RentalType expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", expected);
		return this;
	}

	BookingAssertion hasRentalPlaceIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
		return this;
	}

	BookingAssertion hasTenantIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
		return this;
	}

	BookingAssertion containsAllDays(List<LocalDate> expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("days", expected);
		return this;
	}
}
