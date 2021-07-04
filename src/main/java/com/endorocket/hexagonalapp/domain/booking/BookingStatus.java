package com.endorocket.hexagonalapp.domain.booking;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public enum BookingStatus {
  OPEN, ACCEPTED, REJECTED;

  private static final Map<BookingStatus, List<BookingStatus>> ALLOWED_TRANSITIONS = Map.of(
      REJECTED, emptyList(),
      ACCEPTED, emptyList(),
      OPEN, List.of(REJECTED, ACCEPTED)
  );

  BookingStatus moveTo(BookingStatus bookingStatus) {
    if (!ALLOWED_TRANSITIONS.get(this).contains(bookingStatus)) {
      throw new NotAllowedBookingStatusTransitionException(this, bookingStatus);
    }
    return bookingStatus;
  }
}
