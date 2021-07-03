package com.endorocket.hexagonalapp.application.hotelroomoffer;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomAvailabilityException;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomNotFoundException;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOffer;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferAssertion;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.HotelRoomOfferRepository;
import com.endorocket.hexagonalapp.domain.hotelroomoffer.NotAllowedMoneyValueException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomOfferApplicationServiceTest {
  private static final String HOTEL_ROOM_ID = "1234";
  private static final LocalDate START = LocalDate.of(2020, 10, 11);
  private static final LocalDate END = LocalDate.of(2020, 10, 20);
  private static final BigDecimal PRICE = BigDecimal.valueOf(123);

  private final ArgumentCaptor<HotelRoomOffer> captor = ArgumentCaptor.forClass(HotelRoomOffer.class);

  private final HotelRoomOfferRepository hotelRoomOfferRepository = mock(HotelRoomOfferRepository.class);
  private final HotelRoomRepository hotelRoomRepository = mock(HotelRoomRepository.class);

  private final HotelRoomOfferApplicationService service = new HotelRoomOfferApplicationService(hotelRoomOfferRepository, hotelRoomRepository);

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

  @Test
  void shouldRecognizeHotelRoomDoesNotExist() {
    givenNotExistingHotelRoom();

    HotelRoomNotFoundException actual = assertThrows(HotelRoomNotFoundException.class, () -> service.add(givenHotelRoomOfferDto()));

    assertThat(actual).hasMessage("Hotel room with id: " + HOTEL_ROOM_ID + " does not exist.");
  }

  @Test
  void shouldRecognizePriceLowerThanZero() {
    givenExistingHotelRoom();
    HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, BigDecimal.valueOf(-4), START, END);

    NotAllowedMoneyValueException actual = assertThrows(NotAllowedMoneyValueException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Given -4 is not higher than zero.");
  }

  @Test
  void shouldRecognizePriceEqualToZero() {
    givenExistingHotelRoom();
    HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, BigDecimal.ZERO, START, END);

    NotAllowedMoneyValueException actual = assertThrows(NotAllowedMoneyValueException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Given 0 is not higher than zero.");
  }

  @Test
  void shouldRecognizeThatStartIsAfterEnd() {
    givenExistingHotelRoom();
    HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, END, START);

    HotelRoomAvailabilityException actual = assertThrows(HotelRoomAvailabilityException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Start date: 2020-10-20 of availability is after end date: 2020-10-11.");
  }

  @Test
  void shouldRecognizeThatStartIsBeforeToday() {
    givenExistingHotelRoom();
    LocalDate startDate = LocalDate.now().minusDays(1);
    HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, startDate, LocalDate.now().plusDays(2));

    HotelRoomAvailabilityException actual = assertThrows(HotelRoomAvailabilityException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Start date: " + startDate + " of availability is before today: " + LocalDate.now() + ".");
  }

  @Test
  void shouldFillEndDateWhenEndDateIsEmpty() {
    givenExistingHotelRoom();
    LocalDate startDate = LocalDate.now().plusDays(1);
    HotelRoomOfferDto dto = new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, startDate);

    service.add(dto);

    then(hotelRoomOfferRepository).should().save(captor.capture());

    HotelRoomOffer actual = captor.getValue();
    HotelRoomOfferAssertion.assertThat(actual)
        .hasHotelRoomIdEqualTo(HOTEL_ROOM_ID)
        .hasPriceEqualTo(PRICE)
        .hasAvailabilityEqualTo(startDate, startDate.plusYears(1));
  }

  private void givenExistingHotelRoom() {
    given(hotelRoomRepository.existsById(HOTEL_ROOM_ID)).willReturn(true);
  }

  private void givenNotExistingHotelRoom() {
    given(hotelRoomRepository.existsById(HOTEL_ROOM_ID)).willReturn(false);
  }

  private HotelRoomOfferDto givenHotelRoomOfferDto() {
    return new HotelRoomOfferDto(HOTEL_ROOM_ID, PRICE, START, END);
  }
}
