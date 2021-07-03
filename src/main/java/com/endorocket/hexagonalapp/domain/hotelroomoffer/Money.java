package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.math.BigDecimal;

@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
  private final BigDecimal value;

  private Money(BigDecimal value) {
    this.value = value;
  }

  static Money of(BigDecimal price) {
    if (isNotHigherThanZero(price)) {
      throw new NotAllowedMoneyValueException(price);
    }
    return new Money(price);
  }

  private static boolean isNotHigherThanZero(BigDecimal price) {
    return price.compareTo(BigDecimal.ZERO) <= 0;
  }
}
