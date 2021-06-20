package com.endorocket.hexagonalapp.query.apartment;

public class QueryApartmentRepository {
	private final SpringQueryApartmentRepository springQueryApartmentRepository;

	public QueryApartmentRepository(SpringQueryApartmentRepository springQueryApartmentRepository) {
		this.springQueryApartmentRepository = springQueryApartmentRepository;
	}

	public Iterable<ApartmentReadModel> findAll() {
		return springQueryApartmentRepository.findAll();
	}
}
