package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import com.endorocket.hexagonalapp.infrastructure.json.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
class ApartmentRestControllerSystemTest {
  private static final String OWNER_ID = "1234";
  private static final String STREET = "Krakowska";
  private static final String POSTAL_CODE = "12-345";
  private static final String HOUSE_NUMBER = "1";
  private static final String APARTMENT_NUMBER = "12";
  private static final String CITY = "Wrocław";
  private static final String COUNTRY = "Poland";
  private static final String DESCRIPTION = "Nice apartment";
  private static final Map<String, Double> ROOMS_DEFINITION = Map.of("Toilet", 10.0, "Bedroom", 15.5);

  private final JsonFactory jsonFactory = new JsonFactory();

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

  @Test
  void shouldReturnExistingApartment() throws Exception {
    ApartmentDto apartmentDto = new ApartmentDto(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY, DESCRIPTION, ROOMS_DEFINITION);

    MvcResult mvcResult = mockMvc.perform(post("/apartment").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(apartmentDto)))
        .andExpect(status().isCreated())
        .andReturn();
    String redirectUrlToNewlyCreatedApartment = Objects.requireNonNull(mvcResult.getResponse().getRedirectedUrl());

    mockMvc.perform(get(redirectUrlToNewlyCreatedApartment))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.apartment.ownerId").value(OWNER_ID))
        .andExpect(jsonPath("$.apartment.street").value(STREET))
        .andExpect(jsonPath("$.apartment.postalCode").value(POSTAL_CODE))
        .andExpect(jsonPath("$.apartment.houseNumber").value(HOUSE_NUMBER))
        .andExpect(jsonPath("$.bookingHistory").isEmpty());
  }
}