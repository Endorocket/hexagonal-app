package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomBookingHistoryRepository;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomHistory;

import java.util.Optional;

public class JpaHotelRoomBookingHistoryRepository implements HotelRoomBookingHistoryRepository {
	private final SpringJpaHotelRoomBookingHistoryRepository hotelRoomBookingHistoryRepository;

	public JpaHotelRoomBookingHistoryRepository(SpringJpaHotelRoomBookingHistoryRepository hotelRoomBookingHistoryRepository) {
		this.hotelRoomBookingHistoryRepository = hotelRoomBookingHistoryRepository;
	}

	@Override
	public Optional<HotelRoomHistory> findFor(String hotelRoomId) {
		return hotelRoomBookingHistoryRepository.findById(hotelRoomId);
	}

	@Override
	public void save(HotelRoomHistory hotelRoomHistory) {
		hotelRoomBookingHistoryRepository.save(hotelRoomHistory);
	}
}
