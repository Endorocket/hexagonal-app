package com.endorocket.hexagonalapp.application.hotelroom;

import java.time.LocalDate;
import java.util.List;

public record HotelRoomBookingDto(String hotelId,
                                  int number,
                                  String hotelRoomId,
                                  String tenantId,
                                  List<LocalDate> days) {
}
