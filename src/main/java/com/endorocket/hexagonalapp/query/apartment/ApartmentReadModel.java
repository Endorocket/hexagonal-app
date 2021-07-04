package com.endorocket.hexagonalapp.query.apartment;

import com.endorocket.hexagonalapp.query.space.SpaceReadModel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class ApartmentReadModel {
	@Id
	@GeneratedValue
	private UUID id;

	private String ownerId;

	private String street;
	private String postalCode;

	@Column(name = "house_number")
	private String houseNumber;
	private String apartmentNumber;
	private String city;
	private String country;

	@ElementCollection
	private List<SpaceReadModel> rooms;

	private String description;

	private ApartmentReadModel() {
	}

	ApartmentReadModel(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
	                   String city, String country, List<SpaceReadModel> rooms, String description) {
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
		return id.toString();
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

	public List<SpaceReadModel> getRooms() {
		return rooms;
	}

	public String getDescription() {
		return description;
	}
}
