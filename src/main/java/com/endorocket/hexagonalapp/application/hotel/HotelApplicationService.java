package com.endorocket.hexagonalapp.application.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelFactory;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;

public class HotelApplicationService {
  private final HotelRepository hotelRepository;

  public HotelApplicationService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  public void add(String name, String street, String postalCode, String buildingNumber, String city, String country) {
    Hotel hotel = new HotelFactory().create(name, street, postalCode, buildingNumber, city, country);
    hotelRepository.save(hotel);
  }
}
