package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.application.apartment.ApartmentApplicationService;
import com.endorocket.hexagonalapp.application.apartment.ApartmentBookingDto;
import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentFactory;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBooking;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingAssertion;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.endorocket.hexagonalapp.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Tag("IntegrationTest")
class ApartmentBookingHistoryEventListenerIntegrationTest {
  private static final String OWNER_ID = "1234";
  private static final String STREET = "Krakowska";
  private static final String POSTAL_CODE = "12-345";
  private static final String HOUSE_NUMBER = "1";
  private static final String APARTMENT_NUMBER = "12";
  private static final String CITY = "Wrocław";
  private static final String COUNTRY = "Poland";
  private static final String DESCRIPTION = "Nice apartment";
  private static final Map<String, Double> ROOMS_DEFINITION = Map.of("Toilet", 10.0, "Bedroom", 15.5);
  private static final String TENANT_ID = "126";
  private static final LocalDate START = LocalDate.of(2020, 3, 5);
  private static final LocalDate END = LocalDate.of(2020, 3, 7);

  @Autowired
  private ApartmentApplicationService apartmentApplicationService;

  @Autowired
  private ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Test
  @Transactional
  void shouldUpdateApartmentBookingHistory() {
    String apartmentId = givenExistingApartment();

    apartmentApplicationService.book(apartmentId, givenApartmentBookingDto());
    ApartmentBookingHistory actual = apartmentBookingHistoryRepository.findFor(apartmentId);

    assertThatContainsOneApartmentBookingWithProperValues(actual);
  }

  private ApartmentBookingDto givenApartmentBookingDto() {
    return new ApartmentBookingDto(TENANT_ID, START, END);
  }

  @SuppressWarnings("unchecked")
  private void assertThatContainsOneApartmentBookingWithProperValues(ApartmentBookingHistory actual) {
    Assertions.assertThat(actual).extracting("bookings").satisfies(actualBookings -> {
      List<ApartmentBooking> bookings = (List<ApartmentBooking>) actualBookings;

      Assertions.assertThat(bookings)
          .hasSize(1)
          .allSatisfy(booking ->
              ApartmentBookingAssertion.assertThat(booking)
                  .isStart()
                  .hasOwnerIdEqualTo(OWNER_ID)
                  .hasTenantIdEqualTo(TENANT_ID)
                  .hasBookingPeriodThatHas(START, END));
    });
  }

  private String givenExistingApartment() {
    return apartmentRepository.save(createApartment());
  }

  private Apartment createApartment() {
    return new ApartmentFactory().create(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY, DESCRIPTION, ROOMS_DEFINITION);
  }
}