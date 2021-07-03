package com.endorocket.hexagonalapp.domain.apartmentoffer;

public class ApartmentOffer {
  private final String apartmentId;
  private final Money money;
  private final ApartmentAvailability availability;

  public ApartmentOffer(String apartmentId, Money money, ApartmentAvailability availability) {
    this.apartmentId = apartmentId;
    this.money = money;
    this.availability = availability;
  }
}
