package com.endorocket.hexagonalapp.query.hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
public class HotelReadModel {
	@Id
	private final String id;
	private final String name;
	private final String street;
	private final String postalCode;
	private final String buildingNumber;
	private final String city;
	private final String country;

	HotelReadModel(String id, String name, String street, String postalCode, String buildingNumber, String city, String country) {
		this.id = id;
		this.name = name;
		this.street = street;
		this.postalCode = postalCode;
		this.buildingNumber = buildingNumber;
		this.city = city;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
}
