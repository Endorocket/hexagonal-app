package com.endorocket.hexagonalapp.domain.apartment;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(actual).hasFieldOrPropertyWithValue("ownerId", ownerId);
	}

	private void assertThatHasDescription(Apartment actual, String description) {
		assertThat(actual).hasFieldOrPropertyWithValue("description", description);
	}

	private void assertThatHasAddress(Apartment actual, String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
		assertThat(actual).extracting("address")
			.hasFieldOrPropertyWithValue("street", street)
			.hasFieldOrPropertyWithValue("postalCode", postalCode)
			.hasFieldOrPropertyWithValue("houseNumber", houseNumber)
			.hasFieldOrPropertyWithValue("apartmentNumber", apartmentNumber)
			.hasFieldOrPropertyWithValue("city", city)
			.hasFieldOrPropertyWithValue("country", country);
	}

	@SuppressWarnings("unchecked")
	private void assertThatHasRooms(Apartment actual, Map<String, Double> roomsDefinition) {
		assertThat(actual).extracting("rooms").satisfies(roomsActual -> {
			List<Room> rooms = (List<Room>) roomsActual;
			assertThat(rooms).hasSize(roomsDefinition.size());

			roomsDefinition.forEach((name, squareMeter) -> {
				assertThat(rooms).anySatisfy(room -> {
					assertThat(room)
						.hasFieldOrPropertyWithValue("name", name)
						.hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
				});
			});
		});
	}
}