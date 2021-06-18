package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;

public class JpaHotelRepository implements HotelRepository {
  private final SpringJpaHotelRepository hotelRepository;

  public JpaHotelRepository(SpringJpaHotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public void save(Hotel hotel) {
    hotelRepository.save(hotel);
  }
}
