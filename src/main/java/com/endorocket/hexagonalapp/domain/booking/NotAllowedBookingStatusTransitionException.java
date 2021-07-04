package com.endorocket.hexagonalapp.domain.booking;

public class NotAllowedBookingStatusTransitionException extends RuntimeException {
  private NotAllowedBookingStatusTransitionException(String message) {
    super(message);
  }

  static RuntimeException alreadyRejected() {
    return new NotAllowedBookingStatusTransitionException("Not allowed transition from REJECTED to ACCEPTED booking.");
  }

  static RuntimeException alreadyAccepted() {
    return new NotAllowedBookingStatusTransitionException("Not allowed transition from ACCEPTED to REJECTED booking.");
  }
}
