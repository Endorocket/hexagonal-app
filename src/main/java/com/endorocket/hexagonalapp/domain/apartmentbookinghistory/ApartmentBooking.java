package com.endorocket.hexagonalapp.domain.apartmentbookinghistory;

import java.time.LocalDate;

public class ApartmentBooking {
	private final String ownerId;
	private final String tenantId;
	private final LocalDate periodStart;
	private final LocalDate periodEnd;

	private ApartmentBooking(String ownerId, String tenantId, LocalDate periodStart, LocalDate periodEnd) {
		this.ownerId = ownerId;
		this.tenantId = tenantId;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
	}

	public static ApartmentBooking start(String ownerId, String tenantId, LocalDate periodStart, LocalDate periodEnd) {
		return new ApartmentBooking(ownerId, tenantId, periodStart, periodEnd);
	}
}
