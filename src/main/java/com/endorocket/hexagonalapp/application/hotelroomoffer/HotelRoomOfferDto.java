package com.endorocket.hexagonalapp.application.hotelroomoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

record HotelRoomOfferDto(String hotelId, int number, String hotelRoomId, BigDecimal price, LocalDate start, LocalDate end) {
}
