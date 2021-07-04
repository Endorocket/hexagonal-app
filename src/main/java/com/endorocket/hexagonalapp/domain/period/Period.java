package com.endorocket.hexagonalapp.domain.period;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.List;

@Embeddable
@EqualsAndHashCode
public class Period {

	private LocalDate start;
	private LocalDate end;

	private Period() {
	}

	public Period(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}

	public LocalDate getStart() {
		return start;
	}

	private void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	private void setEnd(LocalDate end) {
		this.end = end;
	}

	public List<LocalDate> asDays() {
		return start.datesUntil(end.plusDays(1)).toList();
	}
}
