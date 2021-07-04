package com.endorocket.hexagonalapp.application.hotelroomoffer;

import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomNotFoundException;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOffer;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class HotelRoomOfferApplicationService {
  private final HotelRepository hotelRepository;
  private final HotelRoomOfferRepository hotelRoomOfferRepository;

  void add(HotelRoomOfferDto dto) {
    Hotel hotel = hotelRepository.findById(dto.hotelId());

    if (!hotel.hasRoomWithNumber(dto.number())) {
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
