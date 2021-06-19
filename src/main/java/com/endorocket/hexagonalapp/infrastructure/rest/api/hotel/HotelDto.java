package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

public record HotelDto(
	String name,
	String street,
	String postalCode,
	String buildingNumber,
	String city,
	String country) {
}
