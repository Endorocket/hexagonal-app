package com.endorocket.hexagonalapp.domain.hotelroom.eventchannel;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomBooked;

public interface EventChannel {
	void publish(HotelRoomBooked hotelRoomBooked);
}
