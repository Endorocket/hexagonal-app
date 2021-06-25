package com.endorocket.hexagonalapp.domain.apartment;

public interface BookingRepository {
	String save(Booking booking);

	Booking findById(String id);
}
