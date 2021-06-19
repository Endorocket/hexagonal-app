package com.endorocket.hexagonalapp.domain.apartment;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Booking {
	private final RentalType apartment;
	@Id
	@GeneratedValue
	private String id;

	private final String rentalPlaceId;
	private final String tenantId;

	@ElementCollection
	private final List<LocalDate> days;

	private Booking(RentalType apartment, String rentalPlaceId, String tenantId, List<LocalDate> days) {
		this.apartment = apartment;
		this.rentalPlaceId = rentalPlaceId;
		this.tenantId = tenantId;
		this.days = days;
	}

	static Booking apartment(String rentalPlaceId, String tenantId, Period period) {
		List<LocalDate> days = period.asDays();
		return new Booking(RentalType.APARTMENT, rentalPlaceId, tenantId, days);
	}

	public static Booking hotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
		return new Booking(RentalType.HOTEL_ROOM, rentalPlaceId, tenantId, days);
	}
}
