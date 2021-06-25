package com.endorocket.hexagonalapp.infrastructure.eventchannel.spring;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.apartment.BookingAccepted;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomBooked;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventChannel implements EventChannel {
	private final ApplicationEventPublisher publisher;

	public SpringEventChannel(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publish(ApartmentBooked apartmentBooked) {
		publisher.publishEvent(apartmentBooked);
	}

	@Override
	public void publish(HotelRoomBooked hotelRoomBooked) {
		publisher.publishEvent(hotelRoomBooked);
	}

	@Override
	public void publish(BookingAccepted bookingAccepted) {
		publisher.publishEvent(bookingAccepted);
	}
}
