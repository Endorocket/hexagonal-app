package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import java.time.LocalDate;

record ApartmentBookingDto(String tenantId,
                                  LocalDate start,
                                  LocalDate end) {
}
