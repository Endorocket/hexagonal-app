package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBooking;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.BookingPeriod;
import org.springframework.context.event.EventListener;

public class ApartmentBookingHistoryEventListener {

	private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

	public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
		this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
	}

	@EventListener
	public void consume(ApartmentBooked apartmentBooked) {
		ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());
		BookingPeriod bookingPeriod = new BookingPeriod(apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd());

		apartmentBookingHistory.add(ApartmentBooking.start(
			apartmentBooked.getCreationDateTime(), apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), bookingPeriod));

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
