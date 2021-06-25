package com.endorocket.hexagonalapp.query.hotelroom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SpringQueryHotelRoomRepository extends CrudRepository<HotelRoomReadModel, String> {
	List<HotelRoomReadModel> findAllByHotelId(String hotelId);
}
