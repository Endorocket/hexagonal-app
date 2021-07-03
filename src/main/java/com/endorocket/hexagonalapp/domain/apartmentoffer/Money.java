package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.math.BigDecimal;

@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
  private final BigDecimal value;

  private Money(BigDecimal value) {
    this.value = value;
  }

  static Money of(BigDecimal price) {
    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new NotAllowedMoneyValueException(price);
    }
    return new Money(price);
  }
}
