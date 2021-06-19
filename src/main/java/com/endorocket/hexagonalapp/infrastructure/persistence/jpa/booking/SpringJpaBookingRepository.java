package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.booking;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaBookingRepository extends CrudRepository<Booking, String> {
}
