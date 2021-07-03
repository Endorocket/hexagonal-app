package com.endorocket.hexagonalapp.application.apartmentoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

record ApartmentOfferDto(String apartmentId, BigDecimal price, LocalDate start, LocalDate end) {
}
