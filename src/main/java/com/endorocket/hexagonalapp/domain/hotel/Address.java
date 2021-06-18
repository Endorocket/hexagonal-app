package com.endorocket.hexagonalapp.domain.hotel;

class Address {
  private final String name;
  private final String street;
  private final String postalCode;
  private final String buildingNumber;
  private final String city;
  private final String country;

  Address(String name, String street, String postalCode, String buildingNumber, String city, String country) {
    this.name = name;
    this.street = street;
    this.postalCode = postalCode;
    this.buildingNumber = buildingNumber;
    this.city = city;
    this.country = country;
  }
}
