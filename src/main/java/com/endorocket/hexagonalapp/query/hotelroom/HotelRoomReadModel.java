package com.endorocket.hexagonalapp.query.hotelroom;

import com.endorocket.hexagonalapp.query.space.SpaceReadModel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
	@Id
	private String id;

	@Column(name = "HOTEL_ID")
	private UUID hotelId;
	private int number;

	@ElementCollection
	@CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
	private List<SpaceReadModel> spaces;

	private String description;

	private HotelRoomReadModel() {
	}

	public String getId() {
		return id;
	}

	public String getHotelId() {
		return hotelId.toString();
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
