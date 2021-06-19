package com.endorocket.hexagonalapp.infrastructure.rest.api.hotelroom;

import com.endorocket.hexagonalapp.application.hotelroom.HotelRoomApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
