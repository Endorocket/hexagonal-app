package com.endorocket.hexagonalapp.domain.apartmentoffer;

public class ApartmentAvailabilityException extends RuntimeException {
  ApartmentAvailabilityException() {
    super("Start date of availability is after end date.");
  }
}
