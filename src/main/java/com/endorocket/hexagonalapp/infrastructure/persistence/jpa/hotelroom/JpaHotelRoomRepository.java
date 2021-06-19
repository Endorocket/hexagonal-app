package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;

public class JpaHotelRoomRepository implements HotelRoomRepository {
  private final SpringJpaHotelRoomRepository hotelRoomRepository;

  public JpaHotelRoomRepository(SpringJpaHotelRoomRepository hotelRoomRepository) {
    this.hotelRoomRepository = hotelRoomRepository;
  }

  @Override
  public void save(HotelRoom hotelRoom) {
    hotelRoomRepository.save(hotelRoom);
  }

	@Override
	public HotelRoom findById(String id) {
		return hotelRoomRepository.findById(id).get();
	}
}
