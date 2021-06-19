package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> {
}
