package com.endorocket.hexagonalapp.query.hotelroom;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface SpringQueryHotelRoomRepository extends CrudRepository<HotelRoomReadModel, String> {
	List<HotelRoomReadModel> findAllByHotelId(String hotelId);
}
