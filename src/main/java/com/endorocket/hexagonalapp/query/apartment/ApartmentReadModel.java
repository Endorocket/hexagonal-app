package com.endorocket.hexagonalapp.query.apartment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APARTMENT")
public class ApartmentReadModel {
	@Id
	@GeneratedValue
	private String id;

	private final String ownerId;

	private final String street;
	private final String postalCode;
	private final String houseNumber;
	private final String apartmentNumber;
	private final String city;
	private final String country;

	@OneToMany
	private final List<RoomReadModel> rooms;

	private final String description;

	ApartmentReadModel(String id, String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
	                   String city, String country, List<RoomReadModel> rooms, String description) {
		this.id = id;
		this.ownerId = ownerId;
		this.street = street;
		this.postalCode = postalCode;
		this.houseNumber = houseNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.country = country;
		this.rooms = rooms;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public List<RoomReadModel> getRooms() {
		return rooms;
	}

	public String getDescription() {
		return description;
	}
}
