package com.endorocket.hexagonalapp.domain.apartment;

import java.time.LocalDateTime;
import java.util.UUID;

class ApartmentBooked {
	private final String eventId;
	private final LocalDateTime creationDateTime;
	private final String id;
	private final String ownerId;
	private final String tenantId;
	private final Period period;

	private ApartmentBooked(String eventId, LocalDateTime creationDateTime, String id, String ownerId, String tenantId, Period period) {

		this.eventId = eventId;
		this.creationDateTime = creationDateTime;
		this.id = id;
		this.ownerId = ownerId;
		this.tenantId = tenantId;
		this.period = period;
	}

	static ApartmentBooked create(String id, String ownerId, String tenantId, Period period) {
		String eventId = UUID.randomUUID().toString();
		LocalDateTime creationDateTime = LocalDateTime.now();

		return new ApartmentBooked(eventId, creationDateTime, id, ownerId, tenantId, period);
	}
}
