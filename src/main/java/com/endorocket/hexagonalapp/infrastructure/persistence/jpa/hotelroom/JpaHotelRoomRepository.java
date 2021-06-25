package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaHotelRoomRepository implements HotelRoomRepository {
  private final SpringJpaHotelRoomRepository hotelRoomRepository;

  JpaHotelRoomRepository(SpringJpaHotelRoomRepository hotelRoomRepository) {
    this.hotelRoomRepository = hotelRoomRepository;
  }

  @Override
  public String save(HotelRoom hotelRoom) {
    return hotelRoomRepository.save(hotelRoom).id();
  }

	@Override
	public HotelRoom findById(String id) {
		return hotelRoomRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new HotelRoomDoesNotExistException(id));
	}
}
