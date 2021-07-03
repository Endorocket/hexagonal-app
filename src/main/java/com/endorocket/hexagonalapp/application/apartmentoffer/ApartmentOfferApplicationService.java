package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentNotFoundException;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;
import lombok.RequiredArgsConstructor;

import static com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

@RequiredArgsConstructor
class ApartmentOfferApplicationService {
  private final ApartmentOfferRepository apartmentOfferRepository;
  private final ApartmentRepository apartmentRepository;

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
