package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaApartmentRepository implements ApartmentRepository {
  private final SpringJpaApartmentRepository apartmentRepository;

  JpaApartmentRepository(SpringJpaApartmentRepository apartmentRepository) {
    this.apartmentRepository = apartmentRepository;
  }

  @Override
  public String save(Apartment apartment) {
    return apartmentRepository.save(apartment).id();
  }

	@Override
	public Apartment findById(String id) {
		return apartmentRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new ApartmentDoesNotExistException(id));
	}

  @Override
  public boolean existsById(String id) {
    return apartmentRepository.existsById(UUID.fromString(id));
  }
}
