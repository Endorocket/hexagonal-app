package com.endorocket.hexagonalapp.domain.eventchannel;

import com.endorocket.hexagonalapp.domain.apartment.ApartmentBooked;
import com.endorocket.hexagonalapp.domain.booking.BookingAccepted;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomBooked;

public interface EventChannel {
	void publish(ApartmentBooked apartmentBooked);

	void publish(HotelRoomBooked hotelRoomBooked);

	void publish(BookingAccepted bookingAccepted);
}
