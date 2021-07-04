package com.endorocket.hexagonalapp.query.space;

import javax.persistence.Embeddable;

@Embeddable
public class SpaceReadModel {
	private String name;
	private Double size;

	private SpaceReadModel() {
	}

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
