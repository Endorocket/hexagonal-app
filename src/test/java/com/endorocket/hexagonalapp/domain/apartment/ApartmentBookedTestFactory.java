package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.period.Period;

public class ApartmentBookedTestFactory {
	public static ApartmentBooked create(String eventId, String apartmentId, String ownerId, String tenantId, Period period) {
		return ApartmentBooked.create(eventId, apartmentId, ownerId, tenantId, period);
	}
}