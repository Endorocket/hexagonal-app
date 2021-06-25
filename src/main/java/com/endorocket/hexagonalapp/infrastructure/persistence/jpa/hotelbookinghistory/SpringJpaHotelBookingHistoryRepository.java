package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelbookinghistory;

import com.endorocket.hexagonalapp.domain.hotelbookinghistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> {
}
