package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentAvailability;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

class ApartmentOfferService {
  private final ApartmentOfferRepository repository;

  public ApartmentOfferService(ApartmentOfferRepository repository) {
    this.repository = repository;
  }

  void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
    ApartmentOffer apartmentOffer = new ApartmentOffer(apartmentId, new Money(price), new ApartmentAvailability(start, end));
    repository.save(apartmentOffer);
  }
}
