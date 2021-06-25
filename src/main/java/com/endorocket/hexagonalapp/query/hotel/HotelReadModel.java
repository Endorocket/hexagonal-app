package com.endorocket.hexagonalapp.query.hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
public class HotelReadModel {
	@Id
	private String id;
	private String name;
	private String street;
	private String postalCode;
	private String buildingNumber;
	private String city;
	private String country;

	private HotelReadModel() {
	}

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
