package com.endorocket.hexagonalapp.domain.apartmentoffer;

import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApartmentOfferAssertion {
  private final ApartmentOffer actual;

  public ApartmentOfferAssertion(ApartmentOffer actual) {
    this.actual = actual;
  }

  public static ApartmentOfferAssertion assertThat(ApartmentOffer actual) {
    return new ApartmentOfferAssertion(actual);
  }

  public ApartmentOfferAssertion hasApartmentIdEqualTo(String expected) {
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("apartmentId", expected);
    return this;
  }

  public ApartmentOfferAssertion hasPriceEqualTo(BigDecimal expected) {
    Assertions.assertThat(actual).extracting("money")
        .hasFieldOrPropertyWithValue("value", expected);
    return this;
  }

  public ApartmentOfferAssertion hasAvailabilityEqualTo(LocalDate start, LocalDate end) {
    Assertions.assertThat(actual).extracting("availability")
        .hasFieldOrPropertyWithValue("start", start)
        .hasFieldOrPropertyWithValue("end", end);
    return this;
  }
}
