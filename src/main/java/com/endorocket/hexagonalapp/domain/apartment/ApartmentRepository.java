package com.endorocket.hexagonalapp.domain.apartment;

public interface ApartmentRepository {
  String save(Apartment apartment);

	Apartment findById(String id);

  boolean existsById(String id);
}
