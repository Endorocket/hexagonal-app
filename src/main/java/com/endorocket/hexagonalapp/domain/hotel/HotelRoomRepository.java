package com.endorocket.hexagonalapp.domain.hotel;

public interface HotelRoomRepository {
  String save(HotelRoom hotelRoom);

	HotelRoom findById(String id);

  boolean existsById(String id);
}
