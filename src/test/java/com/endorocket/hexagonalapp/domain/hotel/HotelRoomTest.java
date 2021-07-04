package com.endorocket.hexagonalapp.domain.hotel;

import com.endorocket.hexagonalapp.domain.space.NotEnoughSpacesGivenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static com.endorocket.hexagonalapp.domain.hotel.HotelRoom.Builder.hotelRoom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HotelRoomTest {
  private static final String HOTEL_ID_1 = "c975b473-2d9e-4f63-baa4-a94c9389f191";
  private static final int ROOM_NUMBER_1 = 13;
  private static final Map<String, Double> SPACES_DEFINITION_1 = Map.of("Toilet", 10.0, "Bedroom", 15.5);
  private static final String DESCRIPTION_1 = "Nice hotel room";

  private static final String HOTEL_ID_2 = "0a78a710-e03d-4635-afd1-604ca7342dbb";
  private static final int ROOM_NUMBER_2 = 14;
  private static final Map<String, Double> SPACES_DEFINITION_2 = Map.of("Bathroom", 12.0, "Kitchen", 12.5);
  private static final String DESCRIPTION_2 = "Very nice hotel room";

  @Test
  void shouldNotBeAbleCreateHotelRoomWithoutSpace() {
    HotelRoom.Builder hotelRoom = hotelRoom()
        .withHotelId(HOTEL_ID_1)
        .withNumber(ROOM_NUMBER_1)
        .withDescription(DESCRIPTION_1);

    NotEnoughSpacesGivenException actual = assertThrows(NotEnoughSpacesGivenException.class, hotelRoom::build);

    assertThat(actual).hasMessage("No spaces given.");
  }

  @Test
  void shouldRecognizeTwoInstancesOfHotelRoomRepresentsTheSameAggregate() {
    HotelRoom hotelRoom2 = createHotelRoom2SameAsHotelRoom1().build();

    HotelRoom actual = createHotelRoom1();

    assertThat(actual.equals(hotelRoom2)).isTrue();
    assertThat(actual.hashCode()).isEqualTo(hotelRoom2.hashCode());
  }

  @ParameterizedTest
  @MethodSource("notTheSameHotelRooms")
  void shouldRecognizeHotelRoomDoesNotRepresentTheSameAggregate(HotelRoom notTheSame) {
    HotelRoom actual = createHotelRoom1();

    assertThat(actual.equals(notTheSame)).isFalse();
    assertThat(actual.hashCode()).isNotEqualTo(notTheSame.hashCode());
  }

  @SuppressWarnings("unused")
  private static Stream<HotelRoom> notTheSameHotelRooms() {
    return Stream.of(
        createHotelRoom2SameAsHotelRoom1().withHotelId(HOTEL_ID_2).build(),
        createHotelRoom2SameAsHotelRoom1().withNumber(ROOM_NUMBER_2).build()
    );
  }

  private HotelRoom createHotelRoom1() {
    return hotelRoom()
        .withHotelId(HOTEL_ID_1)
        .withNumber(ROOM_NUMBER_1)
        .withSpacesDefinition(SPACES_DEFINITION_1)
        .withDescription(DESCRIPTION_1)
        .build();
  }

  private static HotelRoom.Builder createHotelRoom2SameAsHotelRoom1() {
    return hotelRoom()
        .withHotelId(HOTEL_ID_1)
        .withNumber(ROOM_NUMBER_1)
        .withSpacesDefinition(SPACES_DEFINITION_2)
        .withDescription(DESCRIPTION_2);
  }
}
