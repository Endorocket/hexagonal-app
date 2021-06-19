package com.endorocket.hexagonalapp.infrastructure.rest.api.hotelroom;

import com.endorocket.hexagonalapp.application.hotelroom.HotelRoomApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomRestController {
	private final HotelRoomApplicationService hotelRoomApplicationService;

	public HotelRoomRestController(HotelRoomApplicationService hotelRoomApplicationService) {
		this.hotelRoomApplicationService = hotelRoomApplicationService;
	}

	@GetMapping
	public void add(@RequestBody HotelRoomDto hotelRoomDto) {
		hotelRoomApplicationService.add(hotelRoomDto.hotelId(), hotelRoomDto.number(), hotelRoomDto.spacesDefinition(),
			hotelRoomDto.description());
	}

	@PutMapping("/book/{id}")
	public void book(@PathVariable String id, @RequestBody HotelRoomBookingDto hotelRoomBookingDto) {
		hotelRoomApplicationService.book(id, hotelRoomBookingDto.tenantId(), hotelRoomBookingDto.dates());
	}
}
