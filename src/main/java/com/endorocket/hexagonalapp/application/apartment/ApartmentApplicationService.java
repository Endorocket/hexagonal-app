package com.endorocket.hexagonalapp.application.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentEventsPublisher;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.period.Period;
import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.booking.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApartmentApplicationService {

  private final ApartmentRepository apartmentRepository;
  private final BookingRepository bookingRepository;
  private final ApartmentEventsPublisher apartmentEventsPublisher;

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
        .withSpacesDefinition(apartmentDto.spacesDefinition())
        .build();

    return apartmentRepository.save(apartment);
  }

  public void book(String id, ApartmentBookingDto apartmentBookingDto) {
    Apartment apartment = apartmentRepository.findById(id);
    Period period = new Period(apartmentBookingDto.start(), apartmentBookingDto.end());

    Booking booking = apartment.book(apartmentBookingDto.tenantId(), period, apartmentEventsPublisher);

    bookingRepository.save(booking);
  }
}
