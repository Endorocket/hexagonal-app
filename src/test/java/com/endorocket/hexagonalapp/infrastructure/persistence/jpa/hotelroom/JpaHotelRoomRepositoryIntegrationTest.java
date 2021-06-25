package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomAssertion;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomFactory;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JpaHotelRoomRepositoryIntegrationTest {
  private static final String HOTEL_ID = "234";
  private static final int ROOM_NUMBER = 1;
  private static final Map<String, Double> SPACES_DEFINITION = Map.of("Bedroom", 5.5, "Bathroom", 3.0);
  private static final String DESCRIPTION = "Nice hotel room";

  @Autowired
  private HotelRoomRepository hotelRoomRepository;

  private final HotelRoomFactory hotelRoomFactory = new HotelRoomFactory();

  @Test
  void shouldThrowExceptionWhenHotelRoomDoesNotExist() {
    String nonExistingHotelRoomId = UUID.randomUUID().toString();

    HotelRoomDoesNotExistException actual = assertThrows(HotelRoomDoesNotExistException.class, () -> hotelRoomRepository.findById(nonExistingHotelRoomId));

    assertThat(actual).hasMessage("Hotel Room with id " + nonExistingHotelRoomId + " does not exist.");
  }

  @Test
  @Transactional
  void shouldReturnExistingHotelRoom() {
    HotelRoom hotelRoom = createHotelRoom();
    String existingId = hotelRoomRepository.save(hotelRoom);

    HotelRoom actual = hotelRoomRepository.findById(existingId);

    HotelRoomAssertion.assertThat(actual)
        .hasHotelIdEqualTo(HOTEL_ID)
        .hasNumberEqualTo(ROOM_NUMBER)
        .hasSpacesEqualOf(SPACES_DEFINITION)
        .hasDescriptionEqualTo(DESCRIPTION);;
  }

  private HotelRoom createHotelRoom() {
    return hotelRoomFactory.create(HOTEL_ID, ROOM_NUMBER, SPACES_DEFINITION, DESCRIPTION);
  }
}