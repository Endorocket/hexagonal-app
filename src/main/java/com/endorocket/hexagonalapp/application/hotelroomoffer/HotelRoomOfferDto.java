package com.endorocket.hexagonalapp.application.hotelroomoffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
class HotelRoomOfferDto {
  private final String hotelRoomId;
  private final BigDecimal price;
  private final LocalDate start;
  private LocalDate end;
}
