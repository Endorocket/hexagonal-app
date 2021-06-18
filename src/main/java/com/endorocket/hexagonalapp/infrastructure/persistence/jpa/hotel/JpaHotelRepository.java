package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;

public class JpaHotelRepository implements HotelRepository {
  private final SpringJpaHotelRepository springJpaHotelRepository;

  public JpaHotelRepository(SpringJpaHotelRepository springJpaHotelRepository) {
    this.springJpaHotelRepository = springJpaHotelRepository;
  }

  @Override
  public void save(Hotel hotel) {
    springJpaHotelRepository.save(hotel);
  }
}
