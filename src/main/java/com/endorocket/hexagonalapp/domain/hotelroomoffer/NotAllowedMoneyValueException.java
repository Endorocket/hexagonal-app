package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.math.BigDecimal;

public class NotAllowedMoneyValueException extends RuntimeException {

  public NotAllowedMoneyValueException(BigDecimal price) {
    super("Given " + price + " is not higher than zero.");
  }
}
