package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingAssertion;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom.Builder.hotelRoom;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomApplicationServiceTest {
  private static final String HOTEL_ID = "234";
  private static final int ROOM_NUMBER = 1;
  private static final String HOTEL_ROOM_ID = "123";
  private static final String TENANT_ID = "456";
  private static final List<LocalDate> DAYS = List.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2));

  private final HotelRoomRepository hotelRoomRepository = mock(HotelRoomRepository.class);
  private final BookingRepository bookingRepository = mock(BookingRepository.class);
  private final EventChannel eventChannel = mock(EventChannel.class);
  private final HotelRoomApplicationService service = new HotelRoomApplicationService(hotelRoomRepository, bookingRepository, eventChannel);

  @Test
  void shouldCreateBookingWhenHotelRoomBooked() {
    givenHotelRoom();

    service.book(HOTEL_ROOM_ID, givenHotelRoomBookingDto());

    thenBookingShouldBeCreated();
  }

  private HotelRoomBookingDto givenHotelRoomBookingDto() {
    return new HotelRoomBookingDto(TENANT_ID, DAYS);
  }

  private void givenHotelRoom() {
    HotelRoom hotelRoom = createHotelRoom();
    given(hotelRoomRepository.findById(HOTEL_ROOM_ID)).willReturn(hotelRoom);
  }

  private HotelRoom createHotelRoom() {
    return hotelRoom()
        .withHotelId(HOTEL_ID)
        .withNumber(ROOM_NUMBER)
        .withSpacesDefinition(Map.of("Bedroom", 5.5, "Bathroom", 3.0))
        .withDescription("Nice hotel room")
        .build();
  }

  private void thenBookingShouldBeCreated() {
    ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
    then(bookingRepository).should().save(captor.capture());

    BookingAssertion.assertThat(captor.getValue())
        .isHotelRoom()
        .hasTenantIdEqualTo(TENANT_ID)
        .containsAllDays(DAYS);
  }
}