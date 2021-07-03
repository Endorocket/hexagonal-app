package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import javax.annotation.Nullable;
import java.time.LocalDate;

@SuppressWarnings("PMD.UnusedPrivateField")
class HotelRoomAvailability {
  private final LocalDate start;
  private final LocalDate end;

  private HotelRoomAvailability(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
  }

  static HotelRoomAvailability of(LocalDate start, @Nullable LocalDate end) {
    if (end == null) {
      end = start.plusYears(1);
    }
    if (start.isAfter(end)) {
      throw HotelRoomAvailabilityException.startAfterEnd(start, end);
    }
    if (start.isBefore(LocalDate.now())) {
      throw HotelRoomAvailabilityException.startFromPast(start);
    }
    return new HotelRoomAvailability(start, end);
  }
}
