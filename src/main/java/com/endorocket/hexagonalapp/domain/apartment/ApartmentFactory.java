package com.endorocket.hexagonalapp.domain.apartment;

import java.util.Map;

import static com.endorocket.hexagonalapp.domain.apartment.Apartment.Builder.apartment;

public class ApartmentFactory {
	public Apartment create(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
	                        String city, String country, String description, Map<String, Double> roomsDefinition) {
		return apartment()
				.withOwnerId(ownerId)
				.withStreet(street)
				.withPostalCode(postalCode)
				.withHouseNumber(houseNumber)
				.withApartmentNumber(apartmentNumber)
				.withCity(city)
				.withCountry(country)
				.withDescription(description)
				.withRoomsDefinition(roomsDefinition)
				.build();
	}
}
