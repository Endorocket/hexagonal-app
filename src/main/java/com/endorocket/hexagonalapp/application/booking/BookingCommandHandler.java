package com.endorocket.hexagonalapp.application.booking;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.springframework.context.event.EventListener;

public class BookingCommandHandler {
	private final BookingRepository bookingRepository;
	private final EventChannel eventChannel;

	public BookingCommandHandler(BookingRepository bookingRepository, EventChannel eventChannel) {
		this.bookingRepository = bookingRepository;
		this.eventChannel = eventChannel;
	}

	@EventListener
	public void reject(BookingReject bookingReject) {
		Booking booking = bookingRepository.findById(bookingReject.getBookingId());
		booking.reject();
	}

	@EventListener
	public void accept(BookingAccept bookingAccept) {
		Booking booking = bookingRepository.findById(bookingAccept.getBookingId());
		booking.accept(eventChannel);
	}
}
