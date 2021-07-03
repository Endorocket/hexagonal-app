package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

class ApartmentOfferService {
  private final ApartmentOfferRepository repository;

  public ApartmentOfferService(ApartmentOfferRepository repository) {
    this.repository = repository;
  }

  void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {

  }
}
