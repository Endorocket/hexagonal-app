package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelbookinghistory;

import com.endorocket.hexagonalapp.domain.hotelbookinghistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> {
}
