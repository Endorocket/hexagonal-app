package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Booking {
  private RentalType rentalType;
  @Id
  @GeneratedValue
  private UUID id;

  private String rentalPlaceId;
  private String tenantId;

  @ElementCollection
  private List<LocalDate> days;

  @Enumerated(EnumType.STRING)
  private BookingStatus bookingStatus = BookingStatus.OPEN;

  private Booking() {
  }

  private Booking(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
    this.rentalType = rentalType;
    this.rentalPlaceId = rentalPlaceId;
    this.tenantId = tenantId;
    this.days = days;
  }

  static Booking apartment(String rentalPlaceId, String tenantId, Period period) {
    List<LocalDate> days = period.asDays();
    return new Booking(RentalType.APARTMENT, rentalPlaceId, tenantId, days);
  }

  public static Booking hotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
    return new Booking(RentalType.HOTEL_ROOM, rentalPlaceId, tenantId, days);
  }

  public UUID getId() {
    return id;
  }

  public String id() {
    if (id == null) {
      return null;
    }
    return id.toString();
  }

  public void reject() {
    bookingStatus = BookingStatus.REJECTED;
  }

  public void accept(EventChannel eventChannel) {
    bookingStatus = BookingStatus.ACCEPTED;
    BookingAccepted bookingAccepted = BookingAccepted.create(rentalType, rentalPlaceId, tenantId, days);
    eventChannel.publish(bookingAccepted);
  }
}
