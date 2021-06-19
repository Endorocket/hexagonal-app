package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {
}
