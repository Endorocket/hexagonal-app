package com.endorocket.hexagonalapp.domain.hotelbookinghistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class HotelRoomBooking {
	@Id
	@GeneratedValue
	private UUID id;

	private LocalDateTime bookingTime;
	private String tenantId;

	@ElementCollection
	private List<LocalDate> days;

	private HotelRoomBooking() {
	}

	public HotelRoomBooking(LocalDateTime bookingTime, String tenantId, List<LocalDate> days) {
		this.bookingTime = bookingTime;
		this.tenantId = tenantId;
		this.days = days;
	}
}
