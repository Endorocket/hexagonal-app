package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentNotFoundException;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;

import static com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

class ApartmentOfferService {
  private final ApartmentOfferRepository apartmentOfferRepository;
  private final ApartmentRepository apartmentRepository;

  public ApartmentOfferService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
    this.apartmentOfferRepository = apartmentOfferRepository;
    this.apartmentRepository = apartmentRepository;
  }

  void add(ApartmentOfferDto dto) {
    if (!apartmentRepository.existsById(dto.apartmentId())) {
      throw new ApartmentNotFoundException(dto.apartmentId());
    }
    ApartmentOffer apartmentOffer = apartmentOffer()
        .withApartmentId(dto.apartmentId())
        .withMoney(dto.price())
        .withAvailability(dto.start(), dto.end())
        .build();
    apartmentOfferRepository.save(apartmentOffer);
  }
}
