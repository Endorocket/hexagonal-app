package com.endorocket.hexagonalapp.query.hotel;

import org.springframework.data.repository.CrudRepository;

interface SpringQueryHotelRepository extends CrudRepository<HotelReadModel, String> {
}
