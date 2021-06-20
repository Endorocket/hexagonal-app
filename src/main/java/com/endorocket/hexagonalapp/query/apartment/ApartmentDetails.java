package com.endorocket.hexagonalapp.query.apartment;

public class ApartmentDetails {
	private final ApartmentReadModel apartment;
	private final ApartmentBookingHistoryReadModel apartmentBookingHistory;

	ApartmentDetails(ApartmentReadModel apartment, ApartmentBookingHistoryReadModel apartmentBookingHistory) {
		this.apartment = apartment;
		this.apartmentBookingHistory = apartmentBookingHistory;
	}

	public ApartmentReadModel getApartment() {
		return apartment;
	}

	public ApartmentBookingHistoryReadModel getApartmentBookingHistory() {
		return apartmentBookingHistory;
	}
}
