package com.endorocket.hexagonalapp.domain.apartment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ApartmentBooked {
	private final String eventId;
	private final LocalDateTime creationDateTime;
	private final String id;
	private final String ownerId;
	private final String tenantId;
	private final LocalDate periodStart;
	private final LocalDate periodEnd;

	private ApartmentBooked(String eventId, LocalDateTime creationDateTime, String id, String ownerId, String tenantId, Period period) {
		this.eventId = eventId;
		this.creationDateTime = creationDateTime;
		this.id = id;
		this.ownerId = ownerId;
		this.tenantId = tenantId;
		periodStart = period.getStart();
		periodEnd = period.getEnd();
	}

	static ApartmentBooked create(String id, String ownerId, String tenantId, Period period) {
		String eventId = UUID.randomUUID().toString();
		LocalDateTime creationDateTime = LocalDateTime.now();

		return new ApartmentBooked(eventId, creationDateTime, id, ownerId, tenantId, period);
	}

	public String getEventId() {
		return eventId;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public String getId() {
		return id;
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
