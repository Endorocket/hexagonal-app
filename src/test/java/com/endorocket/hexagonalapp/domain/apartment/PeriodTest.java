package com.endorocket.hexagonalapp.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

	@Test
	void shouldReturnPeriodAsDays() {
		LocalDate start = LocalDate.of(2021, 1, 1);
		LocalDate end = LocalDate.of(2021, 1, 4);
		Period period = new Period(start, end);

		List<LocalDate> actual = period.asDays();
		assertThat(actual).hasSize(4);
		assertThatContainsValidDates(actual);
	}

	private void assertThatContainsValidDates(List<LocalDate> actual) {
		assertThat(actual).anySatisfy(dayActual -> assertThat(dayActual).isEqualTo(LocalDate.of(2021, 1, 1)));
		assertThat(actual).anySatisfy(dayActual -> assertThat(dayActual).isEqualTo(LocalDate.of(2021, 1, 2)));
		assertThat(actual).anySatisfy(dayActual -> assertThat(dayActual).isEqualTo(LocalDate.of(2021, 1, 3)));
		assertThat(actual).anySatisfy(dayActual -> assertThat(dayActual).isEqualTo(LocalDate.of(2021, 1, 4)));
	}
}