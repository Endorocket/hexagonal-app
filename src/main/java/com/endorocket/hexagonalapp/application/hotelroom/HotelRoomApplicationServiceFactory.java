package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.booking.BookingRepository;
import com.endorocket.hexagonalapp.domain.clock.Clock;
import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomEventsPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelRoomApplicationServiceFactory {

  @Bean
  HotelRoomApplicationService hotelRoomApplicationService(
      HotelRepository hotelRepository, BookingRepository bookingRepository, EventChannel eventChannel) {
    HotelRoomEventsPublisher hotelRoomEventsPublisher = new HotelRoomEventsPublisher(new EventIdFactory(), new Clock(), eventChannel);
    return new HotelRoomApplicationService(hotelRepository, bookingRepository, hotelRoomEventsPublisher);
  }
}
