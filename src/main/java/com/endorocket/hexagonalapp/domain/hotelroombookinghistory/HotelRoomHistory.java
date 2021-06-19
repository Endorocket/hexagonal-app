package com.endorocket.hexagonalapp.domain.hotelroombookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HotelRoomHistory {

	@Id
	private final String hotelRoomId;

	@OneToMany
	private final List<HotelRoomBooking> bookings = new ArrayList<>();

	public HotelRoomHistory(String hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}

	public void add(HotelRoomBooking hotelRoomBooking) {
		bookings.add(hotelRoomBooking);
	}
}
