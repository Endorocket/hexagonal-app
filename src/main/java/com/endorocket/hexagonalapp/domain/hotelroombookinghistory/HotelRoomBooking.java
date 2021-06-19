package com.endorocket.hexagonalapp.domain.hotelroombookinghistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class HotelRoomBooking {
	private final BookingStep bookingStep;
	private final LocalDateTime bookingTime;
	private final String tenantId;

	@ElementCollection
	private final List<LocalDate> days;

	private HotelRoomBooking(BookingStep bookingStep, LocalDateTime bookingTime, String tenantId, List<LocalDate> days) {
		this.bookingStep = bookingStep;
		this.bookingTime = bookingTime;
		this.tenantId = tenantId;
		this.days = days;
	}

	public static HotelRoomBooking start(LocalDateTime bookingTime, String tenantId, List<LocalDate> days) {
		return new HotelRoomBooking(BookingStep.START, bookingTime, tenantId, days);
	}
}
