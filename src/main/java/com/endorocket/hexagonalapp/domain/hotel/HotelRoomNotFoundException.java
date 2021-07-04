package com.endorocket.hexagonalapp.domain.hotel;

public class HotelRoomNotFoundException extends RuntimeException {
  public HotelRoomNotFoundException(String hotelRoomId) {
    super("Hotel room with id: " + hotelRoomId + " does not exist.");
  }
}
