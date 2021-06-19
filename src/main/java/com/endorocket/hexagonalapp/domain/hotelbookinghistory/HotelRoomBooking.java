package com.endorocket.hexagonalapp.domain.hotelbookinghistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class HotelRoomBooking {
	private final LocalDateTime bookingTime;
	private final String tenantId;

	@ElementCollection
	private final List<LocalDate> days;

	public HotelRoomBooking(LocalDateTime bookingTime, String tenantId, List<LocalDate> days) {
		this.bookingTime = bookingTime;
		this.tenantId = tenantId;
		this.days = days;
	}
}
