package com.endorocket.hexagonalapp.domain.hotelroom;

public interface HotelRoomRepository {
  void save(HotelRoom hotelRoom);

	HotelRoom findById(String id);
}
