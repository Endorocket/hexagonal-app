package com.endorocket.hexagonalapp.application.apartment;

import com.endorocket.hexagonalapp.domain.apartment.Address;
import com.endorocket.hexagonalapp.domain.apartment.Apartment;
import com.endorocket.hexagonalapp.domain.apartment.Room;
import com.endorocket.hexagonalapp.domain.apartment.SquareMeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartmentApplicationService {
	public void add(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
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
		Apartment apartment = new Apartment(ownerId, address, description);

	}
}
