package com.endorocket.hexagonalapp.query.hotelroom;

import org.springframework.stereotype.Repository;

@Repository
public class QueryHotelRoomRepository {
	private final SpringQueryHotelRoomRepository hotelRoomRepository;

	public QueryHotelRoomRepository(SpringQueryHotelRoomRepository hotelRoomRepository) {
		this.hotelRoomRepository = hotelRoomRepository;
	}

	public Iterable<HotelRoomReadModel> findAllByHotelId(String hotelId) {
		return hotelRoomRepository.findAllByHotelId(hotelId);
	}
}
