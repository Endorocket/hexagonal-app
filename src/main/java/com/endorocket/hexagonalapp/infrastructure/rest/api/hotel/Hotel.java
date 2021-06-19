package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

import com.endorocket.hexagonalapp.application.hotel.HotelApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class Hotel {
	private final HotelApplicationService hotelApplicationService;

	public Hotel(HotelApplicationService hotelApplicationService) {
		this.hotelApplicationService = hotelApplicationService;
	}

	@GetMapping
	public void add(@RequestBody HotelDto hotelDto) {
		hotelApplicationService.add(hotelDto.name(), hotelDto.street(), hotelDto.postalCode(), hotelDto.buildingNumber(),
			hotelDto.city(), hotelDto.country());
	}
}
