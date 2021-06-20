package com.endorocket.hexagonalapp.query.hotelroom;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
	@Id
	private final String id;

	private final String hotelId;
	private final int number;

	@OneToMany
	private final List<SpaceReadModel> spaces;

	private final String description;

	HotelRoomReadModel(String id, String hotelId, int number, List<SpaceReadModel> spaces, String description) {
		this.id = id;
		this.hotelId = hotelId;
		this.number = number;
		this.spaces = spaces;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getHotelId() {
		return hotelId;
	}

	public int getNumber() {
		return number;
	}

	public List<SpaceReadModel> getSpaces() {
		return spaces;
	}

	public String getDescription() {
		return description;
	}
}
