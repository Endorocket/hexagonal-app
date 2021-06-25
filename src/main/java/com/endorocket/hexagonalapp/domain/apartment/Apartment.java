package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class Apartment {

	@Id
	@GeneratedValue
	private UUID id;

	private String ownerId;

	@Embedded
	private Address address;

	@ElementCollection
	private List<Room> rooms;

	private String description;

	private Apartment() {
	}

	Apartment(String ownerId, Address address, List<Room> rooms, String description) {
		this.ownerId = ownerId;
		this.address = address;
		this.rooms = rooms;
		this.description = description;
	}

	public Booking book(String tenantId, Period period, EventChannel eventChannel) {
		ApartmentBooked apartmentBooked = ApartmentBooked.create(id(), ownerId, tenantId, period);
		eventChannel.publish(apartmentBooked);

		return Booking.apartment(id(), tenantId, period);
	}

	public UUID getId() {
		return id;
	}

	public String id() {
		return id.toString();
	}
}
