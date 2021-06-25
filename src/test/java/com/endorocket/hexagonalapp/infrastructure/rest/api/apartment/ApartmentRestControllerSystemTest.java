package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApartmentRestControllerSystemTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnNothingWhenApartmentDoesNotExist() throws Exception {
    String notExistingId = UUID.randomUUID().toString();

    ResultActions actual = mockMvc.perform(get("/apartment/" + notExistingId));

    actual
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.apartment").isEmpty())
        .andExpect(jsonPath("$.bookingHistory").isEmpty());
  }
}