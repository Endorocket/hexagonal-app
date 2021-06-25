package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaApartmentBookingHistoryRepository implements ApartmentBookingHistoryRepository {
	private final SpringJpaApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

	public JpaApartmentBookingHistoryRepository(SpringJpaApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
		this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
	}

	@Override
	public boolean existsFor(String apartmentId) {
		return apartmentBookingHistoryRepository.existsById(apartmentId);
	}

	@Override
	public ApartmentBookingHistory findFor(String apartmentId) {
		return apartmentBookingHistoryRepository.findById(apartmentId).get();
	}

	@Override
	public void save(ApartmentBookingHistory apartmentBookingHistory) {
		apartmentBookingHistoryRepository.save(apartmentBookingHistory);
	}
}
