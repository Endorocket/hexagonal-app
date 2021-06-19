package com.endorocket.hexagonalapp.domain.hotelroombookinghistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HotelBookingHistory {
	@Id
	private final String hotelId;

	@OneToMany
	private final List<HotelRoomBookingHistory> hotelRoomBookingHistories = new ArrayList<>();

	public HotelBookingHistory(String hotelId) {
		this.hotelId = hotelId;
	}

	public void add(String hotelRoomId, LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
		HotelRoomBookingHistory hotelRoomBookingHistory = findFor(hotelRoomId);
		hotelRoomBookingHistory.add(bookingDateTime, tenantId, days);
	}

	private HotelRoomBookingHistory findFor(String hotelRoomId) {
		return hotelRoomBookingHistories.stream()
			.filter(hotelRoomBookingHistory -> hotelRoomBookingHistory.hasIdEqualTo(hotelRoomId))
			.findFirst()
			.orElseGet(() -> {
				HotelRoomBookingHistory hotelRoomBookingHistory = new HotelRoomBookingHistory(hotelRoomId);
				hotelRoomBookingHistories.add(hotelRoomBookingHistory);
				return hotelRoomBookingHistory;
			});
	}
}
