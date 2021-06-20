package com.endorocket.hexagonalapp.domain.apartment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingAccepted {
	private final String eventId;
	private final LocalDateTime creationDateTime;
	private final String rentalType;
	private final String rentalPlaceId;
	private final String tenantId;
	private final List<LocalDate> days;

	private BookingAccepted(String eventId, LocalDateTime creationDateTime, String rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
		this.eventId = eventId;
		this.creationDateTime = creationDateTime;
		this.rentalType = rentalType;
		this.rentalPlaceId = rentalPlaceId;
		this.tenantId = tenantId;
		this.days = days;
	}

	static BookingAccepted create(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
		String eventId = UUID.randomUUID().toString();
		LocalDateTime creationDateTime = LocalDateTime.now();

		return new BookingAccepted(eventId, creationDateTime, rentalType.name(), rentalPlaceId, tenantId, days);
	}

	public String getEventId() {
		return eventId;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public String getRentalType() {
		return rentalType;
	}

	public String getRentalPlaceId() {
		return rentalPlaceId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public List<LocalDate> getDays() {
		return days;
	}
}