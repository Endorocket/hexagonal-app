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
    if (start.isAfter(end)) {
      throw HotelRoomAvailabilityException.startAfterEnd(start, end);
    }
    if (start.isBefore(LocalDate.now())) {
      throw HotelRoomAvailabilityException.startFromPast(start);
    }
    return new HotelRoomAvailability(start, end);
  }

  static HotelRoomAvailability of(LocalDate start) {
    return HotelRoomAvailability.of(start, start.plusYears(1));
  }
}
