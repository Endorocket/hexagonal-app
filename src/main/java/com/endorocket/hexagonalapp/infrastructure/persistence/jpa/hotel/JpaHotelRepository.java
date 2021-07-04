package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaHotelRepository implements HotelRepository {
  private final SpringJpaHotelRepository hotelRepository;

  public JpaHotelRepository(SpringJpaHotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public void save(Hotel hotel) {
    hotelRepository.save(hotel);
  }

  @Override
  public Hotel findById(String hotelId) {
    return hotelRepository.findById(UUID.fromString(hotelId)).get();
  }
}
