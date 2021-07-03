package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.math.BigDecimal;

@SuppressWarnings("PMD.UnusedPrivateField")
class Money {
  private final BigDecimal value;

  Money(BigDecimal value) {
    this.value = value;
  }
}
