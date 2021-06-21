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
		String room1Name = "Toilet";
		Double room1Size = 10.0;
		String room2Name = "Bedroom";
		Double room2Size = 15.5;
		Map<String, Double> roomsDefinition = Map.of(room1Name, room1Size, room2Name, room2Size);

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