package com.endorocket.hexagonalapp.application.hotelroomoffer;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class HotelRoomOfferService {
  private final HotelRoomOfferRepository hotelRoomOfferRepository;
  private final HotelRoomRepository hotelRoomRepository;

  void add(HotelRoomOfferDto dto) {

  }
}
