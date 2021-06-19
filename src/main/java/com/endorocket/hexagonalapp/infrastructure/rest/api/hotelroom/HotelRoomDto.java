package com.endorocket.hexagonalapp.infrastructure.rest.api.hotelroom;

import java.util.Map;

record HotelRoomDto(String hotelId,
                    int number,
                    Map<String, Double> spacesDefinition,
                    String description) {
}
