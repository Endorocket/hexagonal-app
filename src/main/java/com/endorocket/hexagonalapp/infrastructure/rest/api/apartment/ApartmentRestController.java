package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import com.endorocket.hexagonalapp.application.apartment.ApartmentApplicationService;
import com.endorocket.hexagonalapp.query.apartment.ApartmentDetails;
import com.endorocket.hexagonalapp.query.apartment.ApartmentReadModel;
import com.endorocket.hexagonalapp.query.apartment.QueryApartmentRepository;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {
	private final ApartmentApplicationService apartmentApplicationService;
	private final QueryApartmentRepository queryApartmentRepository;

	public ApartmentRestController(ApartmentApplicationService apartmentApplicationService, QueryApartmentRepository queryApartmentRepository) {
		this.apartmentApplicationService = apartmentApplicationService;
		this.queryApartmentRepository = queryApartmentRepository;
	}

	@PostMapping
	public void add(@RequestBody ApartmentDto apartmentDto) {
		apartmentApplicationService.add(apartmentDto.ownerId(), apartmentDto.street(), apartmentDto.postalCode(),
			apartmentDto.houseNumber(), apartmentDto.apartmentNumber(), apartmentDto.city(),
			apartmentDto.country(), apartmentDto.description(), apartmentDto.roomsDefinition());
	}

	@PutMapping("/book/{id}")
	public void book(@PathVariable String id, @RequestBody ApartmentBookingDto apartmentBookingDto) {
		apartmentApplicationService.book(id, apartmentBookingDto.tenantId(), apartmentBookingDto.start(),
			apartmentBookingDto.end());
	}

	@GetMapping
	public Iterable<ApartmentReadModel> findAll() {
		return queryApartmentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ApartmentDetails findById(@PathVariable String id) {
		return queryApartmentRepository.findById(id);
	}
}
