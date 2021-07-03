package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApartmentOffer {
  private final String apartmentId;
  private final Money money;
  private final ApartmentAvailability availability;

  public ApartmentOffer(String apartmentId, Money money, ApartmentAvailability availability) {
    this.apartmentId = apartmentId;
    this.money = money;
    this.availability = availability;
  }

  public static class Builder {
    private String apartmentId;
    private BigDecimal money;
    private LocalDate start;
    private LocalDate end;

    private Builder() {
    }

    public static Builder apartmentOffer() {
      return new Builder();
    }

    public Builder withApartmentId(String apartmentId) {
      this.apartmentId = apartmentId;
      return this;
    }

    public Builder withMoney(BigDecimal money) {
      this.money = money;
      return this;
    }

    public Builder withAvailability(LocalDate start, LocalDate end) {
      this.start = start;
      this.end = end;
      return this;
    }

    public ApartmentOffer build() {
      Money money = new Money(this.money);
      ApartmentAvailability availability = new ApartmentAvailability(start, end);
      return new ApartmentOffer(apartmentId, money, availability);
    }
  }
}
