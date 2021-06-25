package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomFactory;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class HotelRoomApplicationService {
  private final HotelRoomRepository hotelRoomRepository;
  private final BookingRepository bookingRepository;
  private final EventChannel eventChannel;

  public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, EventChannel eventChannel) {
    this.hotelRoomRepository = hotelRoomRepository;
	  this.bookingRepository = bookingRepository;
	  this.eventChannel = eventChannel;
  }

  public void add(String hotelId, int number, Map<String, Double> spacesDefinition, String description) {
    HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, spacesDefinition, description);
    hotelRoomRepository.save(hotelRoom);
  }

	public void book(String id, String tenantId, List<LocalDate> days) {
		HotelRoom hotelRoom = hotelRoomRepository.findById(id);

		Booking booking = hotelRoom.book(tenantId, days, eventChannel);

    bookingRepository.save(booking);
	}
}
