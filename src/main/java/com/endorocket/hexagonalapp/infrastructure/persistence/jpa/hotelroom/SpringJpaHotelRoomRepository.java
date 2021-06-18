package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaHotelRoomRepository extends CrudRepository<HotelRoom, String> {
}
