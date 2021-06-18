package com.endorocket.hexagonalapp.domain.hotelroom;

import java.util.List;
import java.util.Map;

public class HotelRoomFactory {
  public HotelRoom create(String hotelId, int number, Map<String, Double> spacesDefinition, String description) {
    List<Space> spaces = spacesDefinition.entrySet().stream()
        .map(entry -> {
          String name = entry.getKey();
          Double size = entry.getValue();
          SquareMeter squareMeter = new SquareMeter(size);
          return new Space(name, squareMeter);
        })
        .toList();
    return new HotelRoom(hotelId, number, spaces, description);
  }
}
