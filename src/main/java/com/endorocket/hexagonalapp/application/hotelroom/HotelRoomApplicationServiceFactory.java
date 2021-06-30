package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.clock.Clock;
import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomEventsPublisher;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelRoomApplicationServiceFactory {

  @Bean
  HotelRoomApplicationService create(HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, EventChannel eventChannel) {
    HotelRoomEventsPublisher hotelRoomEventsPublisher = new HotelRoomEventsPublisher(new EventIdFactory(), new Clock(), eventChannel);
    return new HotelRoomApplicationService(hotelRoomRepository, bookingRepository, hotelRoomEventsPublisher);
  }
}
