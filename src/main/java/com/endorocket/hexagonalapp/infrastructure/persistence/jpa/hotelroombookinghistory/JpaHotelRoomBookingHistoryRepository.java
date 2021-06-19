package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelBookingHistory;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelBookingHistoryRepository;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomBookingHistory;

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
