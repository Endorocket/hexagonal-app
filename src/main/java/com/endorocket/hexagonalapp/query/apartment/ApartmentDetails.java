package com.endorocket.hexagonalapp.query.apartment;

public class ApartmentDetails {
	private final ApartmentReadModel apartmentReadModel;
	private final ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel;

	ApartmentDetails(ApartmentReadModel apartmentReadModel, ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel) {
		this.apartmentReadModel = apartmentReadModel;
		this.apartmentBookingHistoryReadModel = apartmentBookingHistoryReadModel;
	}

	public ApartmentReadModel getApartmentReadModel() {
		return apartmentReadModel;
	}

	public ApartmentBookingHistoryReadModel getApartmentBookingHistoryReadModel() {
		return apartmentBookingHistoryReadModel;
	}
}
