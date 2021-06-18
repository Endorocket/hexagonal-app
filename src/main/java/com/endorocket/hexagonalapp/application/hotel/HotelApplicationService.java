package com.endorocket.hexagonalapp.application.hotel;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelFactory;

public class HotelApplicationService {
  public void add(String name, String street, String postalCode, String buildingNumber, String city, String country) {
    Hotel hotel = new HotelFactory().create(name, street, postalCode, buildingNumber, city, country);
  }
}
