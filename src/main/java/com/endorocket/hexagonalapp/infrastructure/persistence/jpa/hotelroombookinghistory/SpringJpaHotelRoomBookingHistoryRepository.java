package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomHistory;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaHotelRoomBookingHistoryRepository extends CrudRepository<HotelRoomHistory, String> {
}
