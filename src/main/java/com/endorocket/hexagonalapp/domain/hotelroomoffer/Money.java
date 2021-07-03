package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.math.BigDecimal;

@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
  private final BigDecimal value;

  private Money(BigDecimal value) {
    this.value = value;
  }

  static Money of(BigDecimal value) {
    return new Money(value);
  }
}
