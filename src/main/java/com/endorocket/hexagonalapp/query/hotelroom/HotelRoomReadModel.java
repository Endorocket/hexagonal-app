package com.endorocket.hexagonalapp.query.hotelroom;

import com.endorocket.hexagonalapp.query.space.SpaceReadModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
	@Id
	private String id;

	private String hotelId;
	private int number;

	@ElementCollection
	@CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
	private List<SpaceReadModel> spaces;

	private String description;

	private HotelRoomReadModel() {
	}

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
