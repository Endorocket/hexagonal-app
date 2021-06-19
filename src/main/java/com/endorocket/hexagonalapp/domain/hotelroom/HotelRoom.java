package com.endorocket.hexagonalapp.domain.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.eventchannel.EventChannel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
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

  public void book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
    HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(id, hotelId, tenantId, days);
    eventChannel.publish(hotelRoomBooked);
  }
}
