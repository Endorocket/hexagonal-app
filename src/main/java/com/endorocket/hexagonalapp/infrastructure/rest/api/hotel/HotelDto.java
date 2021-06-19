package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

record HotelDto(String name,
                String street,
                String postalCode,
                String buildingNumber,
                String city,
                String country) {
}
