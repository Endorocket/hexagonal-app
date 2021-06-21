package com.endorocket.hexagonalapp.domain.apartment;

import org.junit.jupiter.api.Test;

import java.util.Map;

class ApartmentTest {
	@Test
	void shouldCreateApartmentWithAllRequiredFields() {
		String ownerId = "1234";
		String street = "Krakowska";
		String postalCode = "12-345";
		String houseNumber = "1";
		String apartmentNumber = "12";
		String city = "Wrocław";
		String country = "Poland";
		String description = "Nice apartment";
		Map<String, Double> roomsDefinition = Map.of("Toilet", 10.0, "Bedroom", 15.5);

		Apartment actual = new ApartmentFactory().create(ownerId, street, postalCode, houseNumber, apartmentNumber, city, country, description, roomsDefinition);

		assertThatHasOwnerId(actual, ownerId);
		assertThatHasDescription(actual, description);
		assertThatHasAddress(actual, street, postalCode, houseNumber, apartmentNumber, city, country);
		assertThatHasRooms(actual, roomsDefinition);
	}

	private void assertThatHasOwnerId(Apartment actual, String ownerId) {

	}

	private void assertThatHasDescription(Apartment actual, String description) {

	}

	private void assertThatHasAddress(Apartment actual, String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {

	}

	private void assertThatHasRooms(Apartment actual, Map<String, Double> roomsDefinition) {

	}
}