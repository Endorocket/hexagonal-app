package com.endorocket.hexagonalapp.domain.hotelroombookinghistory;

import java.util.Optional;

public interface HotelRoomBookingHistoryRepository {
	Optional<HotelRoomHistory> findFor(String hotelRoomId);

	void save(HotelRoomHistory hotelRoomHistory);
}
