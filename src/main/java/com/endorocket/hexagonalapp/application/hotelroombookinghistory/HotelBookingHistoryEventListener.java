package com.endorocket.hexagonalapp.application.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomBooked;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelBookingHistory;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelBookingHistoryRepository;
import org.springframework.context.event.EventListener;

public class HotelBookingHistoryEventListener {

	private final HotelBookingHistoryRepository hotelBookingHistoryRepository;

	public HotelBookingHistoryEventListener(HotelBookingHistoryRepository hotelBookingHistoryRepository) {
		this.hotelBookingHistoryRepository = hotelBookingHistoryRepository;
	}

	@EventListener
	public void consume(HotelRoomBooked hotelRoomBooked) {
		HotelBookingHistory hotelBookingHistory = getHotelBookingHistoryFor(hotelRoomBooked.getHotelId());

		hotelBookingHistory.add(hotelRoomBooked.getHotelRoomId(), hotelRoomBooked.getCreationDateTime(), hotelRoomBooked.getTenantId(), hotelRoomBooked.getDays());

		hotelBookingHistoryRepository.save(hotelBookingHistory);
	}

	private HotelBookingHistory getHotelBookingHistoryFor(String hotelId) {
		return hotelBookingHistoryRepository.findFor(hotelId)
			.orElse(new HotelBookingHistory(hotelId));
	}
}
