package com.endorocket.hexagonalapp.query.apartment;

import org.springframework.stereotype.Repository;

import java.util.Optional;
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
		Optional<ApartmentReadModel> found = apartmentRepository.findById(UUID.fromString(id));

		if (found.isPresent()) {
			ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = apartmentBookingHistoryRepository.findById(id).get();

			return new ApartmentDetails(found.get(), apartmentBookingHistoryReadModel);
		} else {
			return ApartmentDetails.notExisting();
		}
	}
}
