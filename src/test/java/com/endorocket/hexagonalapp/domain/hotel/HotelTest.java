package com.endorocket.hexagonalapp.domain.hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.endorocket.hexagonalapp.domain.hotel.Hotel.Builder.hotel;
import static org.assertj.core.api.Assertions.assertThat;

class HotelTest {
  private static final String NAME_1 = "Hotel number 1";
  private static final String STREET_1 = "Krakowska";
  private static final String POSTAL_CODE_1 = "12-345";
  private static final String BUILDING_NUMBER_1 = "1";
  private static final String CITY_1 = "Wrocław";
  private static final String COUNTRY_1 = "Poland";

  private static final String NAME_2 = "Hotel number 2";
  private static final String STREET_2 = "Marszałkowska";
  private static final String POSTAL_CODE_2 = "12-222";
  private static final String BUILDING_NUMBER_2 = "12";
  private static final String CITY_2= "Kraków";
  private static final String COUNTRY_2 = "Germany";

  @Test
  void shouldRecognizeTwoInstancesOfHotelRepresentsTheSameAggregate() {
    Hotel hotel2 = createHotel2SameAsHotel1().build();

    Hotel actual = createHotel1();

    assertThat(actual.equals(hotel2)).isTrue();
    assertThat(actual.hashCode()).isEqualTo(hotel2.hashCode());
  }

  @ParameterizedTest
  @MethodSource("notTheSameHotels")
  void shouldRecognizeHotelDoesNotRepresentTheSameAggregate(Hotel notTheSame) {
    Hotel actual = createHotel1();

    assertThat(actual.equals(notTheSame)).isFalse();
    assertThat(actual.hashCode()).isNotEqualTo(notTheSame.hashCode());
  }

  @SuppressWarnings("unused")
  private static Stream<Hotel> notTheSameHotels() {
    return Stream.of(
        createHotel2SameAsHotel1().withName(NAME_2).build(),
        createHotel2SameAsHotel1().withBuildingNumber(BUILDING_NUMBER_2).build(),
        createHotel2SameAsHotel1().withPostalCode(POSTAL_CODE_2).build(),
        createHotel2SameAsHotel1().withCountry(COUNTRY_2).build(),
        createHotel2SameAsHotel1().withCity(CITY_2).build(),
        createHotel2SameAsHotel1().withStreet(STREET_2).build()
    );
  }

  private Hotel createHotel1() {
    return hotel()
        .withName(NAME_1)
        .withStreet(STREET_1)
        .withPostalCode(POSTAL_CODE_1)
        .withBuildingNumber(BUILDING_NUMBER_1)
        .withCity(CITY_1)
        .withCountry(COUNTRY_1)
        .build();
  }

  private static Hotel.Builder createHotel2SameAsHotel1() {
    return hotel()
        .withName(NAME_1)
        .withStreet(STREET_1)
        .withPostalCode(POSTAL_CODE_1)
        .withBuildingNumber(BUILDING_NUMBER_1)
        .withCity(CITY_1)
        .withCountry(COUNTRY_1);
  }
}
