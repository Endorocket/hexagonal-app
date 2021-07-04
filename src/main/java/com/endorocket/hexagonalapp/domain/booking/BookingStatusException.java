package com.endorocket.hexagonalapp.domain.booking;

public class BookingStatusException extends RuntimeException {
  private BookingStatusException(String message) {
    super(message);
  }

  static RuntimeException alreadyRejected() {
    return new BookingStatusException("Accepting already rejected booking is forbidden.");
  }

  static RuntimeException alreadyAccepted() {
    return new BookingStatusException("Rejecting already accepted booking is forbidden.");
  }
}
