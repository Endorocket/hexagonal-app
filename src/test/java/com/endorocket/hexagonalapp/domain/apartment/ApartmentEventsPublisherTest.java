package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.event.EventIdFactory;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import com.endorocket.hexagonalapp.domain.period.Period;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ApartmentEventsPublisherTest {
  private final EventChannel eventChannel = mock(EventChannel.class);
  private final ApartmentEventsPublisher publisher = new ApartmentEventsPublisher(new EventIdFactory(), eventChannel);

  @Test
  void shouldCreateApartmentBooked() {
    ArgumentCaptor<ApartmentBooked> captor = ArgumentCaptor.forClass(ApartmentBooked.class);
    String apartmentId = "12342";
    String ownerId = "456";
    String tenantId = "5464";
    LocalDateTime beforeNow = LocalDateTime.now().minusNanos(1);
    LocalDate periodStart = LocalDate.of(2020, 10, 11);
    LocalDate periodEnd = LocalDate.of(2020, 10, 18);
    Period period = new Period(periodStart, periodEnd);

    publisher.publishApartmentBooked(apartmentId, ownerId, tenantId, period);

    then(eventChannel).should().publish(captor.capture());
    ApartmentBooked actual = captor.getValue();

    assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));
    assertThat(actual.getEventCreationDateTime())
        .isAfter(beforeNow)
        .isBefore(LocalDateTime.now().plusSeconds(1));
    assertThat(actual.getApartmentId()).isEqualTo(apartmentId);
    assertThat(actual.getOwnerId()).isEqualTo(ownerId);
    assertThat(actual.getTenantId()).isEqualTo(tenantId);
    assertThat(actual.getPeriodStart()).isEqualTo(periodStart);
    assertThat(actual.getPeriodEnd()).isEqualTo(periodEnd);
  }
}