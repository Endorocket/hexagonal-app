package com.endorocket.hexagonalapp.infrastructure.persistence.jpa.booking;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaBookingRepository implements BookingRepository {
	private final SpringJpaBookingRepository bookingRepository;

	JpaBookingRepository(SpringJpaBookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public void save(Booking booking) {
		bookingRepository.save(booking);
	}

	@Override
	public Booking findById(String id) {
		return bookingRepository.findById(UUID.fromString(id)).get();
	}
}
