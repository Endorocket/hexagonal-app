package com.endorocket.hexagonalapp.domain.apartmentoffer;

import java.math.BigDecimal;

public class NotAllowedMoneyValueException extends RuntimeException {
  NotAllowedMoneyValueException(BigDecimal price) {
    super("Given " + price + " is lower than zero.");
  }
}
