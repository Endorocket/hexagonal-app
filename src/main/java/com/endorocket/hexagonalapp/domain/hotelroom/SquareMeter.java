package com.endorocket.hexagonalapp.domain.hotelroom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
  private final Double size;

  SquareMeter(Double size) {
    this.size = size;
  }
}
