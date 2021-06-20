package com.endorocket.hexagonalapp.query.apartment;

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
		ApartmentReadModel apartmentReadModel = apartmentRepository.findById(id).get();
		ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = apartmentBookingHistoryRepository.findById(id).get();

		return new ApartmentDetails(apartmentReadModel, apartmentBookingHistoryReadModel);
	}
}
