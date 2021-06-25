package com.endorocket.hexagonalapp.infrastructure.commandregistry.spring;

import com.endorocket.hexagonalapp.application.booking.BookingAccept;
import com.endorocket.hexagonalapp.application.booking.BookingReject;
import com.endorocket.hexagonalapp.application.commandregistry.CommandRegistry;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandRegistry implements CommandRegistry {
	private final ApplicationEventPublisher publisher;

	public SpringCommandRegistry(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void register(BookingReject bookingReject) {
		publisher.publishEvent(bookingReject);
	}

	@Override
	public void register(BookingAccept bookingAccept) {
		publisher.publishEvent(bookingAccept);
	}
}
