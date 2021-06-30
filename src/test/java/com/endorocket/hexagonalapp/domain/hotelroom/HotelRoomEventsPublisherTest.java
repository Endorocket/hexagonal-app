package com.endorocket.hexagonalapp.domain.hotelroom;

import com.endorocket.hexagonalapp.domain.clock.Clock;
import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomEventsPublisherTest {
  private final EventChannel eventChannel = mock(EventChannel.class);
  private final HotelRoomEventsPublisher publisher = new HotelRoomEventsPublisher(new EventIdFactory(), new Clock(), eventChannel);

  @Test
  void shouldCreateHotelRoomBooked() {
    ArgumentCaptor<HotelRoomBooked> captor = ArgumentCaptor.forClass(HotelRoomBooked.class);
    String hotelRoomId = "456";
    String hotelId = "12342";
    String tenantId = "5464";
    LocalDateTime beforeNow = LocalDateTime.now().minusNanos(1);
    List<LocalDate> days = List.of(LocalDate.of(2020, 10, 11), LocalDate.of(2020, 10, 12));

    publisher.publishHotelRoomBooked(hotelRoomId, hotelId, tenantId, days);

    then(eventChannel).should().publish(captor.capture());
    HotelRoomBooked actual = captor.getValue();

    assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));
    assertThat(actual.getEventCreationDateTime())
        .isAfter(beforeNow)
        .isBefore(LocalDateTime.now().plusSeconds(1));
    assertThat(actual.getHotelRoomId()).isEqualTo(hotelRoomId);
    assertThat(actual.getHotelId()).isEqualTo(hotelId);
    assertThat(actual.getTenantId()).isEqualTo(tenantId);
    assertThat(actual.getDays()).isEqualTo(days);
  }
}