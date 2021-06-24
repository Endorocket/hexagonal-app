package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaApartmentRepository extends CrudRepository<Apartment, UUID> {

}
