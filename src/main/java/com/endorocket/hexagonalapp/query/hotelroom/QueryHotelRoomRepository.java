package com.endorocket.hexagonalapp.query.hotelroom;

public class QueryHotelRoomRepository {
	private final SpringQueryHotelRoomRepository hotelRoomRepository;

	public QueryHotelRoomRepository(SpringQueryHotelRoomRepository hotelRoomRepository) {
		this.hotelRoomRepository = hotelRoomRepository;
	}

	public Iterable<HotelRoomReadModel> findAllByHotelId(String hotelId) {
		return hotelRoomRepository.findAllByHotelId(hotelId);
	}
}
