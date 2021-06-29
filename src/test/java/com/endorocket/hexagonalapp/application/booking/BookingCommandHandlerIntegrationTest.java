package com.endorocket.hexagonalapp.application.booking;

import com.endorocket.hexagonalapp.domain.apartment.Booking;
import com.endorocket.hexagonalapp.domain.apartment.BookingAssertion;
import com.endorocket.hexagonalapp.domain.apartment.BookingRepository;
import com.endorocket.hexagonalapp.infrastructure.rest.api.booking.BookingRestController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Tag("IntegrationTest")
class BookingCommandHandlerIntegrationTest {
  private static final String RENTAL_PLACE_ID = "1234";
  private static final String TENANT_ID = "5556";
  private static final List<LocalDate> DAYS = List.of(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2));

  @Autowired
  private BookingRestController bookingRestController;

  @Autowired
  private BookingRepository bookingRepository;

  @Test
  void shouldRejectBooking() {
    String bookingId = givenOpenBooking();

    bookingRestController.reject(bookingId);
    Booking actual = bookingRepository.findById(bookingId);

    BookingAssertion.assertThat(actual)
        .isRejected();
  }

  @Test
  void shouldAcceptBooking() {
    String bookingId = givenOpenBooking();

    bookingRestController.accept(bookingId);
    Booking actual = bookingRepository.findById(bookingId);

    BookingAssertion.assertThat(actual)
        .isAccepted();
  }

  private String givenOpenBooking() {
    return bookingRepository.save(createBooking());
  }

  private Booking createBooking() {
    return Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);
  }
}