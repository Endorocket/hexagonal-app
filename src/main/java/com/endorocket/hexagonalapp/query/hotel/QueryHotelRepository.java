package com.endorocket.hexagonalapp.query.hotel;

import org.springframework.stereotype.Repository;

@Repository
public class QueryHotelRepository {
	private final SpringQueryHotelRepository hotelRepository;

	public QueryHotelRepository(SpringQueryHotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	public Iterable<HotelReadModel> findAll() {
		return hotelRepository.findAll();
	}
}
