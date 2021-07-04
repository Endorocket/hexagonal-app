package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.booking.BookingRepository;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomEventsPublisher;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static com.endorocket.hexagonalapp.domain.hotel.HotelRoom.Builder.hotelRoom;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HotelRoomApplicationService {
  private final HotelRepository hotelRepository;
  private final HotelRoomRepository hotelRoomRepository;
  private final BookingRepository bookingRepository;
  private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

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

    Booking booking = hotelRoom.book(hotelRoomBookingDto.tenantId(), hotelRoomBookingDto.days(), hotelRoomEventsPublisher);

    bookingRepository.save(booking);
  }
}
