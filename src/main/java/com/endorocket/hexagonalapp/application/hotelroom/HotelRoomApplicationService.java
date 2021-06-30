package com.endorocket.hexagonalapp.application.hotelroom;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomEventsPublisher;
import com.endorocket.hexagonalapp.domain.hotelroom.HotelRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static com.endorocket.hexagonalapp.domain.hotelroom.HotelRoom.Builder.hotelRoom;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HotelRoomApplicationService {
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
