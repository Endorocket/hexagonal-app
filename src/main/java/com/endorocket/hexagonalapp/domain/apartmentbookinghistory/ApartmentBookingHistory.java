package com.endorocket.hexagonalapp.domain.apartmentbookinghistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistory {

	@Id
	private String apartmentId;

	@ElementCollection
	private List<ApartmentBooking> bookings = new ArrayList<>();

	public ApartmentBookingHistory(String apartmentId) {
		this.apartmentId = apartmentId;
	}

	public void add(ApartmentBooking apartmentBooking) {
		bookings.add(apartmentBooking);
	}
}
