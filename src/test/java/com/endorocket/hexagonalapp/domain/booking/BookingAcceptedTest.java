package com.endorocket.hexagonalapp.domain.booking;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class BookingAcceptedTest {

	@Test
	void shouldCreateBookingAcceptedWithAllRequiredInformation() {
		String rentalPlaceId = "1234";
		String tenantId = "5678";
		List<LocalDate> days = List.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5));
		LocalDateTime beforeNow = LocalDateTime.now().minusSeconds(1);

		BookingAccepted actual = BookingAccepted.create(RentalType.APARTMENT, rentalPlaceId, tenantId, days);

		assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));
		assertThat(actual.getEventCreationDateTime())
			.isAfter(beforeNow)
			.isBefore(LocalDateTime.now().plusSeconds(1));
		assertThat(actual.getRentalType()).isEqualTo("APARTMENT");
		assertThat(actual.getRentalPlaceId()).isEqualTo(rentalPlaceId);
		assertThat(actual.getTenantId()).isEqualTo(tenantId);
		assertThat(actual.getDays()).isEqualTo(days);
	}
}