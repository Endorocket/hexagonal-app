package com.endorocket.hexagonalapp.infrastructure.rest.api.hotel;

import com.endorocket.hexagonalapp.application.hotel.HotelApplicationService;
import com.endorocket.hexagonalapp.query.hotel.HotelReadModel;
import com.endorocket.hexagonalapp.query.hotel.QueryHotelRepository;
import com.endorocket.hexagonalapp.query.hotelroom.HotelRoomReadModel;
import com.endorocket.hexagonalapp.query.hotelroom.QueryHotelRoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelRestController {
	private final HotelApplicationService hotelApplicationService;
	private final QueryHotelRepository queryHotelRepository;
	private final QueryHotelRoomRepository queryHotelRoomRepository;

	public HotelRestController(HotelApplicationService hotelApplicationService, QueryHotelRepository queryHotelRepository, QueryHotelRoomRepository queryHotelRoomRepository) {
		this.hotelApplicationService = hotelApplicationService;
		this.queryHotelRepository = queryHotelRepository;
		this.queryHotelRoomRepository = queryHotelRoomRepository;
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

	@GetMapping("/{id}/room")
	public Iterable<HotelRoomReadModel> findAllHotelRoomsByHotelId(@PathVariable String id) {
		return queryHotelRoomRepository.findAllByHotelId(id);
	}
}
