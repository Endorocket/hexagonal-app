package com.endorocket.hexagonalapp.application.apartment;

import java.util.Map;

public record ApartmentDto(String ownerId,
                    String street,
                    String postalCode,
                    String houseNumber,
                    String apartmentNumber,
                    String city,
                    String country,
                    String description,
                    Map<String, Double> roomsDefinition) {
}
