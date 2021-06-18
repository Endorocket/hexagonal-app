package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;

public class JpaApartmentRepository implements ApartmentRepository {
  private final SpringJpaApartmentRepository springJpaApartmentRepository;

  JpaApartmentRepository(SpringJpaApartmentRepository springJpaApartmentRepository) {
    this.springJpaApartmentRepository = springJpaApartmentRepository;
  }

  @Override
  public void save(Apartment apartment) {
    springJpaApartmentRepository.save(apartment);
  }
}
