package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

class HotelRoomDoesNotExistException extends RuntimeException {
  HotelRoomDoesNotExistException(String id) {
    super("Hotel Room with id " + id + " does not exist.");
  }
}
