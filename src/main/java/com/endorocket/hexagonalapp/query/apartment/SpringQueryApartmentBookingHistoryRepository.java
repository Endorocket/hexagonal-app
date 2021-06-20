package com.endorocket.hexagonalapp.query.apartment;

import org.springframework.data.repository.CrudRepository;

public interface SpringQueryApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistoryReadModel, String> {
}
