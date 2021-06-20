package com.endorocket.hexagonalapp.application.booking;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import org.springframework.context.event.EventListener;

public class BookingCommandHandler {
	private final BookingRepository bookingRepository;

	public BookingCommandHandler(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@EventListener
	public void reject(BookingReject bookingReject) {
		Booking booking = bookingRepository.findById(bookingReject.getBookingId());
		booking.reject();
	}
}
