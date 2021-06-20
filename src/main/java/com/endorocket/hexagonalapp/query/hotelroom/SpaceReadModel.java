package com.endorocket.hexagonalapp.query.hotelroom;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL_ROOM_SPACE")
public class SpaceReadModel {
	private final String name;
	private final Double size;

	SpaceReadModel(String name, Double size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public Double getSize() {
		return size;
	}
}
