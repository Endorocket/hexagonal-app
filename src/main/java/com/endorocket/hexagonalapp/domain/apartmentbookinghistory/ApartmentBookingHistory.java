package com.endorocket.hexagonalapp.domain.apartmentbookinghistory;

import com.endorocket.hexagonalapp.domain.period.Period;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
@SuppressWarnings("PMD.UnusedPrivateField")
public class ApartmentBookingHistory {

	@Id
	private String apartmentId;

	@ElementCollection
	private List<ApartmentBooking> bookings = new ArrayList<>();

	private ApartmentBookingHistory() {
	}

	public ApartmentBookingHistory(String apartmentId) {
		this.apartmentId = apartmentId;
	}

	public void addBookingStart(LocalDateTime eventCreationDateTime, String ownerId, String tenantId, Period period) {
		add(ApartmentBooking.start(eventCreationDateTime, ownerId, tenantId, period));
	}

	private void add(ApartmentBooking apartmentBooking) {
		bookings.add(apartmentBooking);
	}
}
