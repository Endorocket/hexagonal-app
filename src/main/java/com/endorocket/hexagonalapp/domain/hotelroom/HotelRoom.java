package com.endorocket.hexagonalapp.domain.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoom {
  @Id
  @GeneratedValue
  private String id;

  private final String hotelId;

  private final int number;

  @OneToMany
  private final List<Space> spaces;

  private final String description;

  HotelRoom(String hotelId, int number, List<Space> spaces, String description) {
    this.hotelId = hotelId;
    this.number = number;
    this.spaces = spaces;
    this.description = description;
  }

  public Booking book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
    HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(id, hotelId, tenantId, days);
    eventChannel.publish(hotelRoomBooked);

    return Booking.hotelRoom(id, tenantId, days);
  }
}
