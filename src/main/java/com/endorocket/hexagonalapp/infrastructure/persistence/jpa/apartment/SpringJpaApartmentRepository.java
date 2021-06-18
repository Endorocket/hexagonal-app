package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import org.springframework.data.repository.CrudRepository;

interface SpringJpaApartmentRepository extends CrudRepository<Apartment, String> {

}
