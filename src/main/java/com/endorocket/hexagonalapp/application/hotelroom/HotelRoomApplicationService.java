package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.hotelroom.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomFactory;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class HotelRoomApplicationService {
  private final HotelRoomRepository hotelRoomRepository;
  private final EventChannel eventChannel;

  public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository, EventChannel eventChannel) {
    this.hotelRoomRepository = hotelRoomRepository;
	  this.eventChannel = eventChannel;
  }

  public void add(String hotelId, int number, Map<String, Double> spacesDefinition, String description) {
    HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, spacesDefinition, description);
    hotelRoomRepository.save(hotelRoom);
  }

	public void book(String id, String tenantId, List<LocalDate> dates) {
		HotelRoom hotelRoom = hotelRoomRepository.findById(id);

		hotelRoom.book(tenantId, dates, eventChannel);
	}
}
