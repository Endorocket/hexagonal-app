package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;

public class JpaHotelRoomRepository implements HotelRoomRepository {
  private final SpringJpaHotelRoomRepository springJpaHotelRoomRepository;

  public JpaHotelRoomRepository(SpringJpaHotelRoomRepository springJpaHotelRoomRepository) {
    this.springJpaHotelRoomRepository = springJpaHotelRoomRepository;
  }

  @Override
  public void save(HotelRoom hotelRoom) {
    springJpaHotelRoomRepository.save(hotelRoom);
  }
}
