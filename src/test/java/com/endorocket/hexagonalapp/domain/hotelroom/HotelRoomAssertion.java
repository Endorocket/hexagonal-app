package com.endorocket.hexagonalapp.domain.hotelroom;

import com.endorocket.hexagonalapp.domain.space.Space;
import com.endorocket.hexagonalapp.domain.space.SpacesAssertion;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

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
  public HotelRoomAssertion hasSpacesDefinitionEqualOf(Map<String, Double> expected) {
    Assertions.assertThat(actual).extracting("spaces").satisfies(spacesActual -> {
      List<Space> spaces = (List<Space>) spacesActual;
      SpacesAssertion.assertThat(spaces)
          .hasSize(expected.size())
          .hasAllSpacesFrom(expected);
    });
    return this;
  }

  public HotelRoomAssertion hasDescriptionEqualTo(String expected) {
    Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", expected);
    return this;
  }
}
