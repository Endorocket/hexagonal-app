package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface SpringJpaHotelRepository extends CrudRepository<Hotel, String> {
}
