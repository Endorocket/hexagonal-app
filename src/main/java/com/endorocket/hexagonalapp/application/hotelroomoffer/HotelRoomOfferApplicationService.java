package com.endorocket.hexagonalapp.application.hotelroomoffer;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomNotFoundException;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOffer;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HotelRoomOfferApplicationService {
  private final HotelRoomOfferRepository hotelRoomOfferRepository;
  private final HotelRoomRepository hotelRoomRepository;

  void add(HotelRoomOfferDto dto) {
    if (!hotelRoomRepository.existsById(dto.hotelRoomId())) {
      throw new HotelRoomNotFoundException(dto.hotelRoomId());
    }
    HotelRoomOffer hotelRoomOffer = HotelRoomOffer.Builder.hotelRoomOffer()
        .withHotelRoomId(dto.hotelRoomId())
        .withMoney(dto.price())
        .withAvailability(dto.start(), dto.end())
        .build();

    hotelRoomOfferRepository.save(hotelRoomOffer);
  }
}
