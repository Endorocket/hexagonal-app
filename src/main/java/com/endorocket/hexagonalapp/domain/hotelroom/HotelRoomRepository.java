package com.endorocket.hexagonalapp.domain.hotelroom;

public interface HotelRoomRepository {
  String save(HotelRoom hotelRoom);

	HotelRoom findById(String id);

  boolean existsById(String id);
}
