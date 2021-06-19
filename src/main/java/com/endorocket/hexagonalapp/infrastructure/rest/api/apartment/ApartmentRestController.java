package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import com.endorocket.hexagonalapp.application.apartment.ApartmentApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {
	private final ApartmentApplicationService apartmentApplicationService;

	public ApartmentRestController(ApartmentApplicationService apartmentApplicationService) {
		this.apartmentApplicationService = apartmentApplicationService;
	}

	@PostMapping
	public void add(@RequestBody ApartmentDto apartmentDto) {
		apartmentApplicationService.add(apartmentDto.ownerId(), apartmentDto.street(), apartmentDto.postalCode(),
			apartmentDto.houseNumber(), apartmentDto.apartmentNumber(), apartmentDto.city(),
			apartmentDto.country(), apartmentDto.description(), apartmentDto.roomsDefinition());
	}
}
