package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelbookinghistory;

import com.endorocket.hexagonalapp.domain.hotelbookinghistory.HotelBookingHistory;
import com.endorocket.hexagonalapp.domain.hotelbookinghistory.HotelBookingHistoryRepository;

import java.util.Optional;

public class JpaHotelRoomBookingHistoryRepository implements HotelBookingHistoryRepository {
	private final SpringJpaHotelBookingHistoryRepository hotelBookingHistoryRepository;

	public JpaHotelRoomBookingHistoryRepository(SpringJpaHotelBookingHistoryRepository hotelBookingHistoryRepository) {
		this.hotelBookingHistoryRepository = hotelBookingHistoryRepository;
	}

	@Override
	public Optional<HotelBookingHistory> findFor(String hotelId) {
		return hotelBookingHistoryRepository.findById(hotelId);
	}

	@Override
	public void save(HotelBookingHistory hotelBookingHistory) {
		hotelBookingHistoryRepository.save(hotelBookingHistory);
	}
}
