package com.endorocket.hexagonalapp.infrastructure.rest.api.hotelroom;

import java.time.LocalDate;
import java.util.List;

record HotelRoomBookingDto(String tenantId,
                           List<LocalDate> days) {
}
