package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.math.BigDecimal;

@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
  private final BigDecimal value;

  private Money(BigDecimal value) {
    this.value = value;
  }

  static Money of(BigDecimal price) {
    if (isLowerThanZero(price)) {
      throw new NotAllowedMoneyValueException(price);
    }
    return new Money(price);
  }

  private static boolean isLowerThanZero(BigDecimal price) {
    return price.compareTo(BigDecimal.ZERO) < 0;
  }
}
