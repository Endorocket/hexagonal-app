package com.endorocket.hexagonalapp.application.apartmentbookinghistory;

import com.endorocket.hexagonalapp.application.apartment.ApartmentApplicationService;
import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApartmentBookingHistoryEventListenerIntegrationTest {

  @Autowired
  private ApartmentApplicationService apartmentApplicationService;

}