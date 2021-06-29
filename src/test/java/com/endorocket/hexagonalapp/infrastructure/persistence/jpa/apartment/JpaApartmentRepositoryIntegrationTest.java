package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentAssertion;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentFactory;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

  private final ApartmentFactory apartmentFactory = new ApartmentFactory();

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
        .hasAddressEqualTo(STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY)
        .hasRoomsEqualsTo(ROOMS_DEFINITION);
  }

  @Test
  @Transactional
  void shouldReturnExistingApartmentWeWant() {
    Apartment apartment1 = apartmentFactory.create("1234", "Floriańska", "98-765", "12", "34", "Kraków", "Poland", "The greatest apartment", Map.of("Room1", 50.0));
    apartmentRepository.save(apartment1);
    Apartment apartment = createApartment();
    String existingId = apartmentRepository.save(apartment);
    Apartment apartment2 = apartmentFactory.create("5692", "Floriańska", "98-999", "10", "42", "Kraków", "Poland", "Great apartment", Map.of("Room4", 100.0));
    apartmentRepository.save(apartment2);
    Apartment apartment3 = apartmentFactory.create("2083", "Floriańska", "98-123", "11", "22", "Kraków", "Poland", "Not so bad apartment", Map.of("Room3", 30.0));
    apartmentRepository.save(apartment3);


    Apartment actual = apartmentRepository.findById(existingId);

    ApartmentAssertion.assertThat(actual)
        .hasOwnerIdEqualTo(OWNER_ID)
        .hasDescriptionEqualTo(DESCRIPTION)
        .hasAddressEqualTo(STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY)
        .hasRoomsEqualsTo(ROOMS_DEFINITION);
  }

  private Apartment createApartment() {
    return apartmentFactory.create(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY, DESCRIPTION, ROOMS_DEFINITION);
  }
}