package com.endorocket.hexagonalapp.infrastructure.rest.api.hotelroom;

import com.endorocket.hexagonalapp.application.hotelroom.HotelRoomApplicationService;
import com.endorocket.hexagonalapp.application.hotelroom.HotelRoomBookingDto;
import com.endorocket.hexagonalapp.application.hotelroom.HotelRoomDto;
import com.endorocket.hexagonalapp.query.hotelroom.HotelRoomReadModel;
import com.endorocket.hexagonalapp.query.hotelroom.QueryHotelRoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomRestController {
	private final HotelRoomApplicationService hotelRoomApplicationService;
	private final QueryHotelRoomRepository queryHotelRoomRepository;

	public HotelRoomRestController(HotelRoomApplicationService hotelRoomApplicationService, QueryHotelRoomRepository queryHotelRoomRepository) {
		this.hotelRoomApplicationService = hotelRoomApplicationService;
		this.queryHotelRoomRepository = queryHotelRoomRepository;
	}

	@GetMapping
	public void add(@RequestBody HotelRoomDto hotelRoomDto) {
		hotelRoomApplicationService.add(hotelRoomDto);
	}

	@PutMapping("/book/{id}")
	public void book(@PathVariable String id, @RequestBody HotelRoomBookingDto hotelRoomBookingDto) {
		hotelRoomApplicationService.book(hotelRoomBookingDto);
	}

	@GetMapping("/hotel/{hotelId}")
	public Iterable<HotelRoomReadModel> findAllHotelRoomsByHotelId(@PathVariable String hotelId) {
		return queryHotelRoomRepository.findAllByHotelId(hotelId);
	}
}
