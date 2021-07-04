package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.booking.BookingRepository;
import com.endorocket.hexagonalapp.domain.hotel.Hotel;
import com.endorocket.hexagonalapp.domain.hotel.HotelRepository;
import com.endorocket.hexagonalapp.domain.hotel.HotelRoomEventsPublisher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HotelRoomApplicationService {
  private final HotelRepository hotelRepository;
  private final BookingRepository bookingRepository;
  private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

  public String add(HotelRoomDto hotelRoomDto) {
    Hotel hotel = hotelRepository.findById(hotelRoomDto.hotelId());

    hotel.addRoom(hotelRoomDto.number(), hotelRoomDto.spacesDefinition(), hotelRoomDto.description());

    hotelRepository.save(hotel);
    return hotel.getIdOfRoom(hotelRoomDto.number());
  }

  public void book(HotelRoomBookingDto hotelRoomBookingDto) {
    Hotel hotel = hotelRepository.findById(hotelRoomBookingDto.hotelId());

    Booking booking = hotel.bookRoom(
        hotelRoomBookingDto.number(), hotelRoomBookingDto.tenantId(), hotelRoomBookingDto.days(), hotelRoomEventsPublisher);

    bookingRepository.save(booking);
  }
}
