package com.endorocket.hexagonalapp.query.apartment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistoryReadModel {

	@Id
	private String apartmentId;

	@ElementCollection
	private List<ApartmentBookingReadModel> bookings = new ArrayList<>();

	private ApartmentBookingHistoryReadModel() {
	}

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
