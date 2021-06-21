package com.endorocket.hexagonalapp.domain.apartment;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class ApartmentTest {
	private static final String OWNER_ID = "1234";
	private static final String STREET = "Krakowska";
	private static final String POSTAL_CODE = "12-345";
	private static final String HOUSE_NUMBER = "1";
	private static final String APARTMENT_NUMBER = "12";
	private static final String CITY = "Wrocław";
	private static final String COUNTRY = "Poland";
	private static final String DESCRIPTION = "Nice apartment";
	private static final Map<String, Double> ROOMS_DEFINITION = Map.of("Toilet", 10.0, "Bedroom", 15.5);

	@Test
	void shouldCreateApartmentWithAllRequiredFields() {
		Apartment actual = new ApartmentFactory().create(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY, DESCRIPTION, ROOMS_DEFINITION);

		assertThatHasOwnerId(actual, OWNER_ID);
		assertThatHasDescription(actual, DESCRIPTION);
		assertThatHasAddress(actual, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY);
		assertThatHasRooms(actual, ROOMS_DEFINITION);
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

			roomsDefinition.forEach((name, squareMeter) ->
				assertThat(rooms).anySatisfy(hasRoomThat(name, squareMeter)));
		});
	}

	private Consumer<Room> hasRoomThat(String name, Double squareMeter) {
		return room -> assertThat(room)
			.hasFieldOrPropertyWithValue("name", name)
			.hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
	}
}