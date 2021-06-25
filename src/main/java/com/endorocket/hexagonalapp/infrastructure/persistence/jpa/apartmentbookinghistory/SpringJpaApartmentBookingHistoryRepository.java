package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {
}
