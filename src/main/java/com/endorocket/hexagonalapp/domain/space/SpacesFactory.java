package com.endorocket.hexagonalapp.domain.space;

import java.util.List;
import java.util.Map;

public class SpacesFactory {
  private SpacesFactory() {
  }

  public static List<Space> create(Map<String, Double> spacesDefinition) {
    return spacesDefinition.entrySet().stream()
        .map(SpacesFactory::asSpace)
        .toList();
  }

  private static Space asSpace(Map.Entry<String, Double> entry) {
    String name = entry.getKey();
    Double size = entry.getValue();
    SquareMeter squareMeter = new SquareMeter(size);
    return new Space(name, squareMeter);
  }
}
