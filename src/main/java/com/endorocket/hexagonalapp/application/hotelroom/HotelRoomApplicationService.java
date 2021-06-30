package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import org.springframework.stereotype.Service;

import static com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom.Builder.hotelRoom;

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

  public void add(HotelRoomDto hotelRoomDto) {
    HotelRoom hotelRoom = hotelRoom()
        .withHotelId(hotelRoomDto.hotelId())
        .withNumber(hotelRoomDto.number())
        .withSpacesDefinition(hotelRoomDto.spacesDefinition())
        .withDescription(hotelRoomDto.description())
        .build();
    hotelRoomRepository.save(hotelRoom);
  }

	public void book(String id, HotelRoomBookingDto hotelRoomBookingDto) {
		HotelRoom hotelRoom = hotelRoomRepository.findById(id);

		Booking booking = hotelRoom.book(hotelRoomBookingDto.tenantId(), hotelRoomBookingDto.days(), eventChannel);

    bookingRepository.save(booking);
	}
}
