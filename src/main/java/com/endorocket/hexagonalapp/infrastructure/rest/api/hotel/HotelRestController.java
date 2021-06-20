package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

import com.endorocket.hexagonalapp.application.hotel.HotelApplicationService;
import com.endorocket.hexagonalapp.query.hotel.HotelReadModel;
import com.endorocket.hexagonalapp.query.hotel.QueryHotelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelRestController {
	private final HotelApplicationService hotelApplicationService;
	private final QueryHotelRepository queryHotelRepository;

	public HotelRestController(HotelApplicationService hotelApplicationService, QueryHotelRepository queryHotelRepository) {
		this.hotelApplicationService = hotelApplicationService;
		this.queryHotelRepository = queryHotelRepository;
	}

	@GetMapping
	public void add(@RequestBody HotelDto hotelDto) {
		hotelApplicationService.add(hotelDto.name(), hotelDto.street(), hotelDto.postalCode(), hotelDto.buildingNumber(),
			hotelDto.city(), hotelDto.country());
	}

	@GetMapping
	public Iterable<HotelReadModel> findAll() {
		return queryHotelRepository.findAll();
	}
}
