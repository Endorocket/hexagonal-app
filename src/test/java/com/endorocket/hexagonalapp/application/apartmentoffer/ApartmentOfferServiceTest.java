package com.endorocket.hexagonalapp.application.apartmentoffer;

import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOffer;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferAssertion;
import com.endorocket.hexagonalapp.domain.apartmentoffer.ApartmentOfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentOfferServiceTest {
  private static final String APARTMENT_ID = "1234";
  private static final LocalDate START = LocalDate.of(2020, 10, 11);
  private static final LocalDate END = LocalDate.of(2020, 10, 20);
  private static final BigDecimal PRICE = BigDecimal.valueOf(123);

  private final ApartmentOfferRepository repository = mock(ApartmentOfferRepository.class);

  private final ApartmentOfferService service = new ApartmentOfferService(repository);

  @Test
  void shouldCreateApartmentOffer() {
    ArgumentCaptor<ApartmentOffer> captor = ArgumentCaptor.forClass(ApartmentOffer.class);
    givenExistingApartment();

    service.add(APARTMENT_ID, PRICE, START, END);

    then(repository).should().save(captor.capture());

    ApartmentOffer actual = captor.getValue();
    ApartmentOfferAssertion.assertThat(actual)
        .hasApartmentIdEqualTo(APARTMENT_ID)
        .hasPriceEqualTo(PRICE)
        .hasAvailabilityEqualTo(START, END);
  }

  private void givenExistingApartment() {

  }

}
