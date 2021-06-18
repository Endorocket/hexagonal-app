package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;

public class JpaApartmentRepository implements ApartmentRepository {
  private final SpringJpaApartmentRepository apartmentRepository;

  JpaApartmentRepository(SpringJpaApartmentRepository apartmentRepository) {
    this.apartmentRepository = apartmentRepository;
  }

  @Override
  public void save(Apartment apartment) {
    apartmentRepository.save(apartment);
  }
}
