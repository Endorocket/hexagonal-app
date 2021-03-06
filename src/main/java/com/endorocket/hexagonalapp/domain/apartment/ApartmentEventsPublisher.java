package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.period.Period;

public class ApartmentEventsPublisher {
  private final EventIdFactory eventIdFactory;
  private final EventChannel eventChannel;

  public ApartmentEventsPublisher(EventIdFactory eventIdFactory, EventChannel eventChannel) {
    this.eventIdFactory = eventIdFactory;
    this.eventChannel = eventChannel;
  }

  void publishApartmentBooked(String apartmentId, String ownerId, String tenantId, Period period) {
    ApartmentBooked apartmentBooked = ApartmentBooked.create(eventIdFactory.create(), apartmentId, ownerId, tenantId, period);
    eventChannel.publish(apartmentBooked);
  }
}
