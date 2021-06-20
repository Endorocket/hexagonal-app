package com.endorocket.hexagonalapp.query.hotel;

public class QueryHotelRepository {
	private final SpringQueryHotelRepository hotelRepository;

	public QueryHotelRepository(SpringQueryHotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	public Iterable<HotelReadModel> findAll() {
		return hotelRepository.findAll();
	}
}
