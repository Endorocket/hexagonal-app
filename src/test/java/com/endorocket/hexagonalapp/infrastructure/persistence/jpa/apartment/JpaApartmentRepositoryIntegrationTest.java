package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentAssertion;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaApartmentRepositoryIntegrationTest {
  private static final String OWNER_ID = "1234";
  private static final String STREET = "Krakowska";
  private static final String POSTAL_CODE = "12-345";
  private static final String HOUSE_NUMBER = "1";
  private static final String APARTMENT_NUMBER = "12";
  private static final String CITY = "Wrocław";
  private static final String COUNTRY = "Poland";
  private static final String DESCRIPTION = "Nice apartment";
  private static final Map<String, Double> ROOMS_DEFINITION = Map.of("Toilet", 10.0, "Bedroom", 15.5);

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Test
  void shouldThrowExceptionWhenApartmentDoesNotExist() {
    String nonExistingApartmentId = UUID.randomUUID().toString();
    ApartmentDoesNotExistException actual = assertThrows(ApartmentDoesNotExistException.class, () -> apartmentRepository.findById(nonExistingApartmentId));

    assertThat(actual).hasMessage("Apartment with id " + nonExistingApartmentId + " does not exist");
  }

  @Test
  @Transactional
  void shouldReturnExistingApartment() {
    Apartment apartment = createApartment();
    String existingId = apartmentRepository.save(apartment);

    Apartment actual = apartmentRepository.findById(existingId);

    ApartmentAssertion.assertThat(actual)
        .hasOwnerIdEqualTo(OWNER_ID)
        .hasDescriptionEqualTo(DESCRIPTION)
        .hasApartmentNumber(APARTMENT_NUMBER)
        .hasAddressEqualTo(STREET, POSTAL_CODE, HOUSE_NUMBER, CITY, COUNTRY)
        .hasRoomsEqualsTo(ROOMS_DEFINITION);
  }

  @Test
  @Transactional
  void shouldReturnExistingApartmentWeWant() {
    Apartment apartment1 = apartment()
        .withOwnerId("1234")
        .withStreet("Floriańska")
        .withPostalCode("98-765")
        .withHouseNumber("12")
        .withApartmentNumber("34")
        .withCity("Kraków")
        .withCountry("Poland")
        .withDescription("The greatest apartment")
        .withRoomsDefinition(Map.of("Room1", 50.0))
        .build();
    apartmentRepository.save(apartment1);

    Apartment apartment = createApartment();
    String existingId = apartmentRepository.save(apartment);

    Apartment apartment2 = apartment()
        .withOwnerId("5692")
        .withStreet("Floriańska")
        .withPostalCode("98-999")
        .withHouseNumber("10")
        .withApartmentNumber("42")
        .withCity("Kraków")
        .withCountry("Poland")
        .withDescription("Great apartment")
        .withRoomsDefinition(Map.of("Room4", 100.0))
        .build();
    apartmentRepository.save(apartment2);

    Apartment apartment3 = apartment()
        .withOwnerId("2083")
        .withStreet("Floriańska")
        .withPostalCode("98-123")
        .withHouseNumber("11")
        .withApartmentNumber("22")
        .withCity("Kraków")
        .withCountry("Poland")
        .withDescription("Not so bad apartment")
        .withRoomsDefinition(Map.of("Room3", 30.0))
        .build();
    apartmentRepository.save(apartment3);


    Apartment actual = apartmentRepository.findById(existingId);

    ApartmentAssertion.assertThat(actual)
        .hasOwnerIdEqualTo(OWNER_ID)
        .hasDescriptionEqualTo(DESCRIPTION)
        .hasApartmentNumber(APARTMENT_NUMBER)
        .hasAddressEqualTo(STREET, POSTAL_CODE, HOUSE_NUMBER, CITY, COUNTRY)
        .hasRoomsEqualsTo(ROOMS_DEFINITION);
  }

  private Apartment createApartment() {
    return apartment()
        .withOwnerId(OWNER_ID)
        .withStreet(STREET)
        .withPostalCode(POSTAL_CODE)
        .withHouseNumber(HOUSE_NUMBER)
        .withApartmentNumber(APARTMENT_NUMBER)
        .withCity(CITY)
        .withCountry(COUNTRY)
        .withDescription(DESCRIPTION)
        .withRoomsDefinition(ROOMS_DEFINITION)
        .build();
  }
}