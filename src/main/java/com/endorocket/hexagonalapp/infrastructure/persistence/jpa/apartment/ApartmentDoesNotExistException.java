package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

public class ApartmentDoesNotExistException extends RuntimeException {

  ApartmentDoesNotExistException(String id) {
    super("Apartment with id " + id + " does not exist");
  }
}
