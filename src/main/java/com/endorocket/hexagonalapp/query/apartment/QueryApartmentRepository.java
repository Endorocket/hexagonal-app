package com.endorocket.hexagonalapp.query.apartment;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class QueryApartmentRepository {
	private final SpringQueryApartmentRepository apartmentRepository;
	private final SpringQueryApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

	public QueryApartmentRepository(SpringQueryApartmentRepository apartmentRepository, SpringQueryApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
		this.apartmentRepository = apartmentRepository;
		this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
	}

	public Iterable<ApartmentReadModel> findAll() {
		return apartmentRepository.findAll();
	}

	public ApartmentDetails findById(String id) {
		ApartmentReadModel apartmentReadModel = apartmentRepository.findById(UUID.fromString(id)).get();
		ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = apartmentBookingHistoryRepository.findById(id).get();

		return new ApartmentDetails(apartmentReadModel, apartmentBookingHistoryReadModel);
	}
}
