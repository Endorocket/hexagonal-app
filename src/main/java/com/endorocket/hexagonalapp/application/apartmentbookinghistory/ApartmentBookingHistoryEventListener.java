package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.endorocket.hexagonalapp.domain.period.Period;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApartmentBookingHistoryEventListener {

  private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

  public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
    this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
  }

  @EventListener
  public void consume(ApartmentBooked apartmentBooked) {
    ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());
    Period period = new Period(apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd());

    apartmentBookingHistory.addBookingStart(
        apartmentBooked.getEventCreationDateTime(), apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), period);

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
