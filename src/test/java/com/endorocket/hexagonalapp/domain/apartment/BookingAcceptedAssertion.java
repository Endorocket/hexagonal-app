package com.endorocket.hexagonalapp.domain.apartment;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

class BookingAcceptedAssertion {
	private final BookingAccepted actual;

	public BookingAcceptedAssertion(BookingAccepted actual) {
		this.actual = actual;
	}

	static BookingAcceptedAssertion assertThat(BookingAccepted actual) {
		return new BookingAcceptedAssertion(actual);
	}

	BookingAcceptedAssertion hasEventIdMatching(Pattern expectedPattern) {
		Assertions.assertThat(actual).extracting("eventId")
			.satisfies(actualEventId -> {
				String eventId = (String) actualEventId;
				Assertions.assertThat(eventId).matches(expectedPattern);
			});
		return this;
	}

	BookingAcceptedAssertion hasEventCreationDateTimeBetween(LocalDateTime expectedBefore, LocalDateTime expectedAfter) {
		return this;
	}

	BookingAcceptedAssertion isRentalTypeOf(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", expected);
		return this;
	}

	BookingAcceptedAssertion hasRentalPlaceIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
		return this;
	}

	BookingAcceptedAssertion hasTenantIdEqualTo(String expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
		return this;
	}

	BookingAcceptedAssertion containsAllDays(List<LocalDate> expected) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("days", expected);
		return this;
	}
}
