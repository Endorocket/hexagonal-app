package com.endorocket.hexagonalapp.application.apartment;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentEventsPublisher;
import com.endorocket.hexagonalapp.domain.apartment.ApartmentRepository;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {

  @Bean
  ApartmentApplicationService apartmentApplicationService(ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
    ApartmentEventsPublisher apartmentEventsPublisher = new ApartmentEventsPublisher(new EventIdFactory(), eventChannel);
    return new ApartmentApplicationService(apartmentRepository, bookingRepository, apartmentEventsPublisher);
  }
}
