package com.endorocket.hexagonalapp.domain.hotelbookinghistory;

import java.util.Optional;

public interface HotelBookingHistoryRepository {
	Optional<HotelBookingHistory> findFor(String hotelId);

	void save(HotelBookingHistory hotelHistory);
}
