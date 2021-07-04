package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.booking.BookingAssertion;
import com.endorocket.hexagonalapp.domain.period.Period;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentTest {
  private static final String OWNER_ID_1 = "1234";
  private static final String STREET_1 = "Krakowska";
  private static final String POSTAL_CODE_1 = "12-345";
  private static final String HOUSE_NUMBER_1 = "1";
  private static final String APARTMENT_NUMBER_1 = "12";
  private static final String CITY_1 = "Wrocław";
  private static final String COUNTRY_1 = "Poland";
  private static final String DESCRIPTION_1 = "Nice apartment";
  private static final Map<String, Double> SPACES_DEFINITION_1 = Map.of("Toilet", 10.0, "Bedroom", 15.5);

  private static final String OWNER_ID_2 = "123456";
  private static final String STREET_2 = "Marszałkowska";
  private static final String POSTAL_CODE_2 = "12-222";
  private static final String HOUSE_NUMBER_2 = "12";
  private static final String APARTMENT_NUMBER_2 = "13";
  private static final String CITY_2 = "Kraków";
  private static final String COUNTRY_2 = "Germany";
  private static final String DESCRIPTION_2 = "Cosy apartment";
  private static final Map<String, Double> SPACES_DEFINITION_2 = Map.of("Bathroom", 12.0, "Kitchen", 12.5);

  private static final String TENANT_ID = "126";
  private static final LocalDate START = LocalDate.of(2020, 3, 5);
  private static final LocalDate MIDDLE = LocalDate.of(2020, 3, 6);
  private static final LocalDate END = LocalDate.of(2020, 3, 7);
  private static final Period PERIOD = new Period(START, END);

  private final ApartmentEventsPublisher apartmentEventsPublisher = mock(ApartmentEventsPublisher.class);

  @Test
  void shouldCreateApartmentWithAllRequiredFields() {
    Apartment actual = createApartment1();

    ApartmentAssertion.assertThat(actual)
        .hasOwnerIdEqualTo(OWNER_ID_1)
        .hasDescriptionEqualTo(DESCRIPTION_1)
        .hasApartmentNumberEqualTo(APARTMENT_NUMBER_1)
        .hasAddressEqualTo(STREET_1, POSTAL_CODE_1, HOUSE_NUMBER_1, CITY_1, COUNTRY_1)
        .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_1);
  }

  @Test
  void shouldCreateBookingOnceBooked() {
    Apartment apartment = createApartment1();

    Booking actual = apartment.book(TENANT_ID, PERIOD, apartmentEventsPublisher);

    BookingAssertion.assertThat(actual)
        .isApartment()
        .hasTenantIdEqualTo(TENANT_ID)
        .containsAllDays(List.of(START, MIDDLE, END));
  }

  @Test
  void shouldPublishApartmentBooked() {
    Apartment apartment = createApartment1();

    apartment.book(TENANT_ID, PERIOD, apartmentEventsPublisher);

    then(apartmentEventsPublisher).should().publishApartmentBooked(any(), eq(OWNER_ID_1), eq(TENANT_ID), eq(new Period(START, END)));
  }

  @Test
  void shouldRecognizeTwoInstancesOfApartmentRepresentsTheSameAggregate() {
    Apartment apartment2 = createApartment2SameAsApartment1().build();

    Apartment actual = createApartment1();

    assertThat(actual.equals(apartment2)).isTrue();
    assertThat(actual.hashCode()).isEqualTo(apartment2.hashCode());
  }

  @ParameterizedTest
  @MethodSource("notTheSameApartments")
  void shouldRecognizeApartmentDoesNotRepresentTheSameAggregate(Apartment notTheSame) {
    Apartment actual = createApartment1();

    assertThat(actual.equals(notTheSame)).isFalse();
    assertThat(actual.hashCode()).isNotEqualTo(notTheSame.hashCode());
  }

  @SuppressWarnings("unused")
  private static Stream<Apartment> notTheSameApartments() {
    return Stream.of(
        createApartment2SameAsApartment1().withOwnerId(OWNER_ID_2).build(),
        createApartment2SameAsApartment1().withApartmentNumber(APARTMENT_NUMBER_2).build(),
        createApartment2SameAsApartment1().withStreet(STREET_2).build(),
        createApartment2SameAsApartment1().withPostalCode(POSTAL_CODE_2).build(),
        createApartment2SameAsApartment1().withHouseNumber(HOUSE_NUMBER_2).build(),
        createApartment2SameAsApartment1().withCity(CITY_2).build(),
        createApartment2SameAsApartment1().withCountry(COUNTRY_2).build()
    );
  }

  private Apartment createApartment1() {
    return apartment()
        .withOwnerId(OWNER_ID_1)
        .withStreet(STREET_1)
        .withPostalCode(POSTAL_CODE_1)
        .withHouseNumber(HOUSE_NUMBER_1)
        .withApartmentNumber(APARTMENT_NUMBER_1)
        .withCity(CITY_1)
        .withCountry(COUNTRY_1)
        .withDescription(DESCRIPTION_1)
        .withSpacesDefinition(SPACES_DEFINITION_1)
        .build();
  }

  private static Apartment.Builder createApartment2SameAsApartment1() {
    return apartment()
        .withOwnerId(OWNER_ID_1)
        .withStreet(STREET_1)
        .withPostalCode(POSTAL_CODE_1)
        .withHouseNumber(HOUSE_NUMBER_1)
        .withApartmentNumber(APARTMENT_NUMBER_1)
        .withCity(CITY_1)
        .withCountry(COUNTRY_1)
        .withDescription(DESCRIPTION_2)
        .withSpacesDefinition(SPACES_DEFINITION_2);
  }
}