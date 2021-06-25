package com.endorocket.hexagonalapp.query.hotel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringQueryHotelRepository extends CrudRepository<HotelReadModel, String> {
}
