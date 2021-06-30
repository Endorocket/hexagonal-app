package com.endorocket.hexagonalapp.application.apartment;

import java.time.LocalDate;

public record ApartmentBookingDto(String tenantId,
                           LocalDate start,
                           LocalDate end) {
}
