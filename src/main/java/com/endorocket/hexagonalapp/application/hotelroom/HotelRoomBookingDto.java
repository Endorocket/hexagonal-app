package com.endorocket.hexagonalapp.application.hotelroom;

import java.time.LocalDate;
import java.util.List;

public record HotelRoomBookingDto(String tenantId,
                           List<LocalDate> days) {
}
