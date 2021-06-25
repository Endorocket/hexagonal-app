package com.endorocket.hexagonalapp.domain.hotelroom;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HotelRoomAssertion {
  private final HotelRoom actual;

  private HotelRoomAssertion(HotelRoom actual) {
    this.actual = actual;
  }

  public static HotelRoomAssertion assertThat(HotelRoom actual) {
    return new HotelRoomAssertion(actual);
  }

  public HotelRoomAssertion hasHotelIdEqualTo(String expected) {
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("hotelId", expected);
    return this;
  }

  public HotelRoomAssertion hasNumberEqualTo(int expected) {
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("number", expected);
    return this;
  }

  @SuppressWarnings("unchecked")
  public HotelRoomAssertion hasSpacesEqualOf(Map<String, Double> spacesDefinition) {
    Assertions.assertThat(actual).extracting("spaces").satisfies(spacesActual -> {
      List<Space> spaces = (List<Space>) spacesActual;
      Assertions.assertThat(spaces).hasSize(spacesDefinition.size());

      spacesDefinition.forEach((name, squareMeter) ->
          Assertions.assertThat(spaces).anySatisfy(hasSpaceThat(name, squareMeter)));
    });
    return this;
  }

  private Consumer<Space> hasSpaceThat(String name, Double squareMeter) {
    return space -> Assertions.assertThat(space)
        .hasFieldOrPropertyWithValue("name", name)
        .hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
  }

  public HotelRoomAssertion hasDescriptionEqualTo(String expected) {
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", expected);
    return this;
  }
}
