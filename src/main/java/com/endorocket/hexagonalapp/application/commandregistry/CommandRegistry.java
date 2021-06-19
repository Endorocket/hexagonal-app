package com.endorocket.hexagonalapp.application.commandregistry;

import com.endorocket.hexagonalapp.application.booking.BookingReject;

public interface CommandRegistry {
	void register(BookingReject bookingReject);
}
