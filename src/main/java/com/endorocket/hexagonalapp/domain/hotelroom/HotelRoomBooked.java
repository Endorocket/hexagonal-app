package com.endorocket.hexagonalapp.domain.hotelroom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class HotelRoomBooked {
	private final String eventId;
	private final LocalDateTime creationDateTime;
	private final String id;
	private final String hotelId;
	private final String tenantId;
	private final List<LocalDate> days;

	private HotelRoomBooked(String eventId, LocalDateTime creationDateTime, String id, String hotelId, String tenantId, List<LocalDate> days) {
		this.eventId = eventId;
		this.creationDateTime = creationDateTime;
		this.id = id;
		this.hotelId = hotelId;
		this.tenantId = tenantId;
		this.days = days;
	}

	static HotelRoomBooked create(String id, String hotelId, String tenantId, List<LocalDate> days) {
		String eventId = UUID.randomUUID().toString();
		LocalDateTime creationDateTime = LocalDateTime.now();
		return new HotelRoomBooked(eventId, creationDateTime, id, hotelId, tenantId, days);
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

	public String getHotelId() {
		return hotelId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public List<LocalDate> getDays() {
		return days;
	}
}
