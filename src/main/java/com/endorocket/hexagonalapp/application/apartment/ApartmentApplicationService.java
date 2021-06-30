package com.endorocket.hexagonalapp.application.apartment;

import com.endorocket.hexagonalapp.domain.apartment.*;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

@Service
public class ApartmentApplicationService {
  private final ApartmentRepository apartmentRepository;
  private final EventChannel eventChannel;
  private final BookingRepository bookingRepository;

  public ApartmentApplicationService(ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
    this.apartmentRepository = apartmentRepository;
    this.eventChannel = eventChannel;
    this.bookingRepository = bookingRepository;
  }

  public String add(ApartmentDto apartmentDto) {
    Apartment apartment = new ApartmentFactory().create(
        apartmentDto.ownerId(), apartmentDto.street(), apartmentDto.postalCode(), apartmentDto.houseNumber(), apartmentDto.apartmentNumber(),
        apartmentDto.city(), apartmentDto.country(), apartmentDto.description(), apartmentDto.roomsDefinition());
    return apartmentRepository.save(apartment);
  }

  public void book(String id, ApartmentBookingDto apartmentBookingDto) {
    Apartment apartment = apartmentRepository.findById(id);
    Period period = new Period(apartmentBookingDto.start(), apartmentBookingDto.end());

    Booking booking = apartment.book(apartmentBookingDto.tenantId(), period, eventChannel);
    bookingRepository.save(booking);
  }
}
