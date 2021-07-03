package com.endorocket.hexagonalapp.domain.apartment;

public class ApartmentNotFoundException extends RuntimeException {

  public ApartmentNotFoundException(String id) {
    super("Apartment with id: " + id + " does not exist.");
  }
}
