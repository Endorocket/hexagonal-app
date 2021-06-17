package com.endorocket.hexagonalapp.domain.apartment;

import java.util.List;
import java.util.Map;

public class ApartmentFactory {
	public Apartment create(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
	                        String city, String country, String description, Map<String, Double> roomsDefinition) {
		Address address = new Address(street, postalCode, houseNumber, apartmentNumber, city, country);
		List<Room> rooms = roomsDefinition.entrySet().stream()
			.map(entry -> {
				String name = entry.getKey();
				Double size = entry.getValue();
				SquareMeter squareMeter = new SquareMeter(size);
				return new Room(name, squareMeter);
			})
			.toList();
		return new Apartment(ownerId, address, rooms, description);
	}
}
