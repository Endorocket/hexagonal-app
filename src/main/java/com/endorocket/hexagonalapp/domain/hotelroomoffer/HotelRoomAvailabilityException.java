package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.time.LocalDate;

public class HotelRoomAvailabilityException extends RuntimeException {
  HotelRoomAvailabilityException(LocalDate start, LocalDate end) {
    super("Start date: " + start + " of availability is after end date: " + end + ".");
  }

  HotelRoomAvailabilityException(LocalDate start) {
    super("Start date: " + start + " of availability is before today: " + LocalDate.now() + ".");
  }
}
