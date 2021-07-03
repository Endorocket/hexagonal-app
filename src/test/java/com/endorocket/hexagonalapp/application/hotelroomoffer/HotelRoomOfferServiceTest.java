package com.endorocket.hexagonalapp.application.hotelroomoffer;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOffer;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferAssertion;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomOfferServiceTest {
  private static final String HOTEL_ROOM_ID = "1234";
  private static final LocalDate START = LocalDate.of(2020, 10, 11);
  private static final LocalDate END = LocalDate.of(2020, 10, 20);
  private static final BigDecimal PRICE = BigDecimal.valueOf(123);

  private final ArgumentCaptor<HotelRoomOffer> captor = ArgumentCaptor.forClass(HotelRoomOffer.class);

  private final HotelRoomOfferRepository hotelRoomOfferRepository = mock(HotelRoomOfferRepository.class);
  private final HotelRoomRepository hotelRoomRepository = mock(HotelRoomRepository.class);

  private final HotelRoomOfferService service = new HotelRoomOfferService(hotelRoomOfferRepository, hotelRoomRepository);

  @Test
  void shouldCreateHotelRoomOfferForExistingHotelRoom() {
    givenExistingHotelRoom();

    service.add(givenHotelRoomOfferDto());

    then(hotelRoomOfferRepository).should().save(captor.capture());

    HotelRoomOffer actual = captor.getValue();
    HotelRoomOfferAssertion.assertThat(actual)
        .hasHotelRoomIdEqualTo(HOTEL_ROOM_ID)
        .hasPriceEqualTo(PRICE)
        .hasAvailabilityEqualTo(START, END);
  }

  private void givenExistingHotelRoom() {
  }

  private HotelRoomOfferDto givenHotelRoomOfferDto() {
    return new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, START, END);
  }
}
