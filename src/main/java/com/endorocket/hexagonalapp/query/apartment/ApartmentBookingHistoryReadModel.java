package com.endorocket.hexagonalapp.query.apartment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistoryReadModel {

	@Id
	private final String apartmentId;

	@OneToMany
	private final List<ApartmentBookingReadModel> bookings = new ArrayList<>();

	ApartmentBookingHistoryReadModel(String apartmentId) {
		this.apartmentId = apartmentId;
	}

	public String getApartmentId() {
		return apartmentId;
	}

	public List<ApartmentBookingReadModel> getBookings() {
		return bookings;
	}
}
