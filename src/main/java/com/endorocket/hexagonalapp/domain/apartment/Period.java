package com.endorocket.hexagonalapp.domain.apartment;

import java.time.LocalDate;
import java.util.List;

public class Period {

	private final LocalDate start;
	private final LocalDate end;

	public Period(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}

	LocalDate getStart() {
		return start;
	}

	LocalDate getEnd() {
		return end;
	}

	List<LocalDate> asDays() {
		return start.datesUntil(end).toList();
	}
}
