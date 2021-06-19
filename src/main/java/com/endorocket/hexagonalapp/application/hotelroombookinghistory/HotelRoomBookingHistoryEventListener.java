package com.endorocket.hexagonalapp.application.hotelroombookinghistory;

import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomBooked;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomBooking;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomBookingHistoryRepository;
import com.endorocket.hexagonalapp.domain.hotelroombookinghistory.HotelRoomHistory;
import org.springframework.context.event.EventListener;

public class HotelRoomBookingHistoryEventListener {

	private final HotelRoomBookingHistoryRepository hotelRoomBookingHistoryRepository;

	public HotelRoomBookingHistoryEventListener(HotelRoomBookingHistoryRepository hotelRoomBookingHistoryRepository) {
		this.hotelRoomBookingHistoryRepository = hotelRoomBookingHistoryRepository;
	}

	@EventListener
	public void consume(HotelRoomBooked hotelRoomBooked) {
		HotelRoomHistory hotelRoomHistory = getHotelRoomBookingHistoryFor(hotelRoomBooked.getHotelRoomId());

		hotelRoomHistory.add(HotelRoomBooking.start(
			hotelRoomBooked.getCreationDateTime(), hotelRoomBooked.getTenantId(), hotelRoomBooked.getDays()));

		hotelRoomBookingHistoryRepository.save(hotelRoomHistory);
	}

	private HotelRoomHistory getHotelRoomBookingHistoryFor(String hotelRoomId) {
		return hotelRoomBookingHistoryRepository.findFor(hotelRoomId)
			.orElse(new HotelRoomHistory(hotelRoomId));
	}
}
