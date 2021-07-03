package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentNotFoundException;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferService {
  private final ApartmentOfferRepository apartmentOfferRepository;
  private final ApartmentRepository apartmentRepository;

  public ApartmentOfferService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
    this.apartmentOfferRepository = apartmentOfferRepository;
    this.apartmentRepository = apartmentRepository;
  }

  void add(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
    if (!apartmentRepository.existsById(apartmentId)) {
      throw new ApartmentNotFoundException(apartmentId);
    }
    ApartmentOffer apartmentOffer = apartmentOffer()
        .withApartmentId(apartmentId)
        .withMoney(price)
        .withAvailability(start, end)
        .build();
    apartmentOfferRepository.save(apartmentOffer);
  }
}
