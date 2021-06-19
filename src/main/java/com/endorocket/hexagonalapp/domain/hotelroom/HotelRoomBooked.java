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
	private final List<LocalDate> dates;

	private HotelRoomBooked(String eventId, LocalDateTime creationDateTime, String id, String hotelId, String tenantId, List<LocalDate> dates) {
		this.eventId = eventId;
		this.creationDateTime = creationDateTime;
		this.id = id;
		this.hotelId = hotelId;
		this.tenantId = tenantId;
		this.dates = dates;
	}

	static HotelRoomBooked create(String id, String hotelId, String tenantId, List<LocalDate> dates) {
		String eventId = UUID.randomUUID().toString();
		LocalDateTime creationDateTime = LocalDateTime.now();
		return new HotelRoomBooked(eventId, creationDateTime, id, hotelId, tenantId, dates);
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

	public List<LocalDate> getDates() {
		return dates;
	}
}
