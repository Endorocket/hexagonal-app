package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.period.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApartmentBooked {
	private final String eventId;
	private final LocalDateTime eventCreationDateTime;
	private final String apartmentId;
	private final String ownerId;
	private final String tenantId;
	private final LocalDate periodStart;
	private final LocalDate periodEnd;

	private ApartmentBooked(String eventId, LocalDateTime eventCreationDateTime, String apartmentId, String ownerId, String tenantId, Period period) {
		this.eventId = eventId;
		this.eventCreationDateTime = eventCreationDateTime;
		this.apartmentId = apartmentId;
		this.ownerId = ownerId;
		this.tenantId = tenantId;
		periodStart = period.getStart();
		periodEnd = period.getEnd();
	}

	static ApartmentBooked create(String eventId, String apartmentId, String ownerId, String tenantId, Period period) {
		LocalDateTime creationDateTime = LocalDateTime.now();
		return new ApartmentBooked(eventId, creationDateTime, apartmentId, ownerId, tenantId, period);
	}

	public String getEventId() {
		return eventId;
	}

	public LocalDateTime getEventCreationDateTime() {
		return eventCreationDateTime;
	}

	public String getApartmentId() {
		return apartmentId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public LocalDate getPeriodStart() {
		return periodStart;
	}

	public LocalDate getPeriodEnd() {
		return periodEnd;
	}
}
