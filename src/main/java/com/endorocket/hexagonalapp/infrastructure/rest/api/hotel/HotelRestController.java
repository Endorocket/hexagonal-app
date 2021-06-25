package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

import com.endorocket.hexagonalapp.application.hotel.HotelApplicationService;
import com.endorocket.hexagonalapp.query.hotel.HotelReadModel;
import com.endorocket.hexagonalapp.query.hotel.QueryHotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelRestController {
	private final HotelApplicationService hotelApplicationService;
	private final QueryHotelRepository queryHotelRepository;

	public HotelRestController(HotelApplicationService hotelApplicationService, QueryHotelRepository queryHotelRepository) {
		this.hotelApplicationService = hotelApplicationService;
		this.queryHotelRepository = queryHotelRepository;
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody HotelDto hotelDto) {
		hotelApplicationService.add(hotelDto.name(), hotelDto.street(), hotelDto.postalCode(), hotelDto.buildingNumber(),
			hotelDto.city(), hotelDto.country());

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public Iterable<HotelReadModel> findAll() {
		return queryHotelRepository.findAll();
	}
}
