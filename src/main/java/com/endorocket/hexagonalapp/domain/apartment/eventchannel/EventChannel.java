package com.endorocket.hexagonalapp.domain.apartment.eventchannel;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;

public interface EventChannel {
	void publish(ApartmentBooked apartmentBooked);
}
