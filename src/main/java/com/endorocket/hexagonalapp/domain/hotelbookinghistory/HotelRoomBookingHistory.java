package com.endorocket.hexagonalapp.domain.hotelbookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HotelRoomBookingHistory {

	@Id
	private String hotelRoomId;

	@OneToMany
	private List<HotelRoomBooking> bookings = new ArrayList<>();

	private HotelRoomBookingHistory() {
	}

	public HotelRoomBookingHistory(String hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}

	public void add(HotelRoomBooking hotelRoomBooking) {
		bookings.add(hotelRoomBooking);
	}

	boolean hasIdEqualTo(String hotelRoomId) {
		return this.hotelRoomId.equals(hotelRoomId);
	}

	void add(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
		bookings.add(new HotelRoomBooking(bookingDateTime, tenantId, days));
	}
}
