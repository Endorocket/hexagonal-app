package com.endorocket.hexagonalapp.domain.booking;

import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.period.Period;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.endorocket.hexagonalapp.domain.booking.BookingStatus.ACCEPTED;
import static com.endorocket.hexagonalapp.domain.booking.BookingStatus.REJECTED;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
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

  public static Booking apartment(String rentalPlaceId, String tenantId, Period period) {
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
    if (bookingStatus == ACCEPTED) {
      throw new NotAllowedBookingStatusTransitionException(bookingStatus, REJECTED);
    }
    bookingStatus = REJECTED;
  }

  public void accept(EventChannel eventChannel) {
    if (bookingStatus == REJECTED) {
      throw new NotAllowedBookingStatusTransitionException(bookingStatus, ACCEPTED);
    }
    bookingStatus = ACCEPTED;
    BookingAccepted bookingAccepted = BookingAccepted.create(rentalType, rentalPlaceId, tenantId, days);
    eventChannel.publish(bookingAccepted);
  }
}
