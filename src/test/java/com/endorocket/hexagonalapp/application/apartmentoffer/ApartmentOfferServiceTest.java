package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentNotFoundException;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferAssertion;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;
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

  private final ApartmentOfferRepository apartmentOfferRepository = mock(ApartmentOfferRepository.class);
  private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);

  private final ApartmentOfferService service = new ApartmentOfferService(apartmentOfferRepository, apartmentRepository);

  @Test
  void shouldCreateApartmentOfferForExistingApartment() {
    ArgumentCaptor<ApartmentOffer> captor = ArgumentCaptor.forClass(ApartmentOffer.class);
    givenExistingApartment();

    service.add(APARTMENT_ID, PRICE, START, END);

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

    ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> service.add(APARTMENT_ID, PRICE, START, END));

    assertThat(actual).hasMessage("Apartment with id: " + APARTMENT_ID + " does not exist.");
  }

  private void givenNotExistingApartment() {
    given(apartmentRepository.existsById(APARTMENT_ID)).willReturn(false);
  }

  private void givenExistingApartment() {
    given(apartmentRepository.existsById(APARTMENT_ID)).willReturn(true);
  }

}
