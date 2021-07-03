package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentNotFoundException;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentAvailabilityException;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferAssertion;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.NotAllowedMoneyValueException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentOfferServiceTest {
  private static final String APARTMENT_ID = "1234";
  private static final LocalDate START = LocalDate.of(2020, 10, 11);
  private static final LocalDate END = LocalDate.of(2020, 10, 20);
  private static final BigDecimal PRICE = BigDecimal.valueOf(123);

  private final ArgumentCaptor<ApartmentOffer> captor = ArgumentCaptor.forClass(ApartmentOffer.class);

  private final ApartmentOfferRepository apartmentOfferRepository = mock(ApartmentOfferRepository.class);
  private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);

  private final ApartmentOfferService service = new ApartmentOfferService(apartmentOfferRepository, apartmentRepository);

  @Test
  void shouldCreateApartmentOfferForExistingApartment() {
    givenExistingApartment();

    service.add(givenApartmentOfferDto());

    then(apartmentOfferRepository).should().save(captor.capture());

    ApartmentOffer actual = captor.getValue();
    ApartmentOfferAssertion.assertThat(actual)
        .hasApartmentIdEqualTo(APARTMENT_ID)
        .hasPriceEqualTo(PRICE)
        .hasAvailabilityEqualTo(START, END);
  }

  @Test
  void shouldRecognizeApartmentDoesNotExist() {
    givenNotExistingApartment();

    ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> service.add(givenApartmentOfferDto()));

    assertThat(actual).hasMessage("Apartment with id: " + APARTMENT_ID + " does not exist.");
  }

  @Test
  void shouldRecognizePriceLowerThanZero() {
    givenExistingApartment();
    ApartmentOfferDto dto = new ApartmentOfferDto(APARTMENT_ID, BigDecimal.valueOf(-12), START, END);

    NotAllowedMoneyValueException actual = assertThrows(NotAllowedMoneyValueException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Given -12 is lower than zero.");
  }

  @Test
  void shouldCreateApartmentOfferWithZeroPrice() {
    givenExistingApartment();

    service.add(new ApartmentOfferDto(APARTMENT_ID, BigDecimal.ZERO, START, END));

    then(apartmentOfferRepository).should().save(captor.capture());

    ApartmentOffer actual = captor.getValue();
    ApartmentOfferAssertion.assertThat(actual)
        .hasApartmentIdEqualTo(APARTMENT_ID)
        .hasPriceEqualTo(BigDecimal.ZERO)
        .hasAvailabilityEqualTo(START, END);
  }

  @Test
  void shouldRecognizeThatStartIsAfterEnd() {
    givenExistingApartment();
    ApartmentOfferDto dto = new ApartmentOfferDto(APARTMENT_ID, PRICE, END, START);

    ApartmentAvailabilityException actual = assertThrows(ApartmentAvailabilityException.class, () -> service.add(dto));

    assertThat(actual).hasMessage("Start date: 2020-10-20 of availability is after end date: 2020-10-11.");
  }

  private ApartmentOfferDto givenApartmentOfferDto() {
    return new ApartmentOfferDto(APARTMENT_ID, PRICE, START, END);
  }

  private void givenNotExistingApartment() {
    given(apartmentRepository.existsById(APARTMENT_ID)).willReturn(false);
  }

  private void givenExistingApartment() {
    given(apartmentRepository.existsById(APARTMENT_ID)).willReturn(true);
  }

}
