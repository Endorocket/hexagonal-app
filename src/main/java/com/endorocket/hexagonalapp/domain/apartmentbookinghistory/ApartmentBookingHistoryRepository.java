package com.endorocket.hexagonalapp.domain.apartmentbookinghistory;

public interface ApartmentBookingHistoryRepository {
	void save(ApartmentBookingHistory apartmentBookingHistory);

	boolean existsFor(String apartmentId);

	ApartmentBookingHistory findFor(String apartmentId);
}
