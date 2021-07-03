package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.time.LocalDate;

@SuppressWarnings("PMD.UnusedPrivateField")
class ApartmentAvailability {
  private final LocalDate start;
  private final LocalDate end;

  ApartmentAvailability(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
  }
}
