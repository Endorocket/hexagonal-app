package com.endorocket.hexagonalapp.domain.apartment;

public interface BookingRepository {
	void save(Booking booking);

	Booking findById(String id);
}
