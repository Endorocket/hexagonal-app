package com.endorocket.hexagonalapp.domain.booking;

public interface BookingRepository {
	String save(Booking booking);

	Booking findById(String id);
}
