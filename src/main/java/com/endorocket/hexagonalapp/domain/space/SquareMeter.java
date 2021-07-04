package com.endorocket.hexagonalapp.domain.space;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateMethod")
class SquareMeter {
  private Double size;

  private SquareMeter() {
  }

  SquareMeter(Double size) {
    this.size = size;
  }

  private Double getSize() {
    return size;
  }

  private void setSize(Double size) {
    this.size = size;
  }
}
