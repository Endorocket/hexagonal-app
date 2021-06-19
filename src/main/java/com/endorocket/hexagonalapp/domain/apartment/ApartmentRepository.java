package com.endorocket.hexagonalapp.domain.apartment;

public interface ApartmentRepository {
  void save(Apartment apartment);

	Apartment findById(String id);
}
