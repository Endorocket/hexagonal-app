package com.endorocket.hexagonalapp.domain.hotelroomoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("PMD.UnusedPrivateField")
public class HotelRoomOffer {
  private final String hotelRoomId;
  private final Money money;
  private final HotelRoomAvailability availability;

  private HotelRoomOffer(String hotelRoomId, Money money, HotelRoomAvailability availability) {
    this.hotelRoomId = hotelRoomId;
    this.money = money;
    this.availability = availability;
  }

  public static class Builder {
    private String hotelRoomId;
    private BigDecimal price;
    private LocalDate start;
    private LocalDate end;

    private Builder() {
    }

    public static Builder hotelRoomOffer() {
      return new Builder();
    }

    public Builder withHotelRoomId(String hotelRoomId) {
      this.hotelRoomId = hotelRoomId;
      return this;
    }

    public Builder withMoney(BigDecimal price) {
      this.price = price;
      return this;
    }

    public Builder withAvailability(LocalDate start, LocalDate end) {
      this.start = start;
      this.end = end;
      return this;
    }

    public HotelRoomOffer build() {
      Money money = Money.of(price);
      return new HotelRoomOffer(hotelRoomId, money, hotelRoomAvailability());
    }

    private HotelRoomAvailability hotelRoomAvailability() {
      if (end == null) {
        return HotelRoomAvailability.of(start);
      }
      return HotelRoomAvailability.of(start, end);
    }
  }
}
