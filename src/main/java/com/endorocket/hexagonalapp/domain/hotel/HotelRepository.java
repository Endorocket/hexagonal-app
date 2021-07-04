package com.endorocket.hexagonalapp.domain.hotel;

public interface HotelRepository {
  void save(Hotel hotel);

  Hotel findById(String hotelId);
}
