package com.endorocket.hexagonalapp.application.hotelroom;

import java.util.Map;

public record HotelRoomDto(String hotelId,
                    int number,
                    Map<String, Double> spacesDefinition,
                    String description) {
}
