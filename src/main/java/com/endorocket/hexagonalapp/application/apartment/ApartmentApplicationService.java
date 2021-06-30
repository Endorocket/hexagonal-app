package com.endorocket.hexagonalapp.application.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.apartment.Period;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;

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
    Apartment apartment = apartment()
        .withOwnerId(apartmentDto.ownerId())
        .withStreet(apartmentDto.street())
        .withPostalCode(apartmentDto.postalCode())
        .withHouseNumber(apartmentDto.houseNumber())
        .withApartmentNumber(apartmentDto.apartmentNumber())
        .withCity(apartmentDto.city())
        .withCountry(apartmentDto.country())
        .withDescription(apartmentDto.description())
        .withRoomsDefinition(apartmentDto.roomsDefinition())
        .build();

    return apartmentRepository.save(apartment);
  }

  public void book(String id, ApartmentBookingDto apartmentBookingDto) {
    Apartment apartment = apartmentRepository.findById(id);
    Period period = new Period(apartmentBookingDto.start(), apartmentBookingDto.end());

    Booking booking = apartment.book(apartmentBookingDto.tenantId(), period, eventChannel);
    bookingRepository.save(booking);
  }
}
