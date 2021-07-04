package com.endorocket.hexagonalapp.domain.booking;

public class NotAllowedBookingStatusTransitionException extends RuntimeException {

  NotAllowedBookingStatusTransitionException(BookingStatus from, BookingStatus to) {
    super("Not allowed transition from " + from + " to " + to + " booking.");
  }
}
