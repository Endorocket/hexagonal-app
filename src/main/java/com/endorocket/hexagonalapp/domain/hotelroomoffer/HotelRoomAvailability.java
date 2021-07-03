package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.time.LocalDate;

@SuppressWarnings("PMD.UnusedPrivateField")
class HotelRoomAvailability {
  private final LocalDate start;
  private final LocalDate end;

  private HotelRoomAvailability(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
  }

  static HotelRoomAvailability of(LocalDate start, LocalDate end) {
    return new HotelRoomAvailability(start, end);
  }
}
