package com.endorocket.hexagonalapp.application.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import org.springframework.stereotype.Service;

import static com.endorocket.hexagonalapp.domain.hotel.Hotel.Builder.hotel;

@Service
public class HotelApplicationService {
  private final HotelRepository hotelRepository;

  public HotelApplicationService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  public void add(HotelDto hotelDto) {
    Hotel hotel = hotel()
        .withName(hotelDto.name())
        .withStreet(hotelDto.street())
        .withPostalCode(hotelDto.postalCode())
        .withBuildingNumber(hotelDto.buildingNumber())
        .withCity(hotelDto.city())
        .withCountry(hotelDto.country())
        .build();
    hotelRepository.save(hotel);
  }
}
