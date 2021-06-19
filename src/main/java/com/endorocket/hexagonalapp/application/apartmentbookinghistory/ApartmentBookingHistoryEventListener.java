package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBooking;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.context.event.EventListener;

public class ApartmentBookingHistoryEventListener {

	private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

	public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
		this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
	}

	@EventListener
	public void consume(ApartmentBooked apartmentBooked) {
		String apartmentId = apartmentBooked.getApartmentId();
		ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentId);

		apartmentBookingHistory.add(ApartmentBooking.start(apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd()));

		apartmentBookingHistoryRepository.save(apartmentBookingHistory);
	}

	private ApartmentBookingHistory getApartmentBookingHistoryFor(String apartmentId) {
		if (apartmentBookingHistoryRepository.existsFor(apartmentId)) {
			return apartmentBookingHistoryRepository.findFor(apartmentId);
		} else {
			return new ApartmentBookingHistory(apartmentId);
		}
	}
}
