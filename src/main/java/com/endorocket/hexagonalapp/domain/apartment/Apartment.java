package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.address.Address;
import com.endorocket.hexagonalapp.domain.booking.Booking;
import com.endorocket.hexagonalapp.domain.period.Period;
import com.endorocket.hexagonalapp.domain.space.Space;
import com.endorocket.hexagonalapp.domain.space.SpacesFactory;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
@SuppressWarnings("PMD.UnusedPrivateField")
public class Apartment {

  @Id
  @GeneratedValue
  private UUID id;

  private String ownerId;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "buildingNumber", column = @Column(name = "house_number"))
  })
  private Address address;

  private String apartmentNumber;

  @ElementCollection
  @CollectionTable(name = "APARTMENT_ROOM", joinColumns = @JoinColumn(name = "APARTMENT_ID"))
  private List<Space> spaces;

  private String description;

  private Apartment() {
  }

  private Apartment(String ownerId, Address address, String apartmentNumber, List<Space> spaces, String description) {
    this.ownerId = ownerId;
    this.address = address;
    this.apartmentNumber = apartmentNumber;
    this.spaces = spaces;
    this.description = description;
  }

  public Booking book(String tenantId, Period period, ApartmentEventsPublisher publisher) {
    publisher.publishApartmentBooked(id(), ownerId, tenantId, period);

    return Booking.apartment(id(), tenantId, period);
  }

  public UUID getId() {
    return id;
  }

  public String id() {
    if (id == null) {
      return null;
    }
    return id.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Apartment apartment = (Apartment) o;
    return Objects.equals(ownerId, apartment.ownerId)
        && Objects.equals(address, apartment.address)
        && Objects.equals(apartmentNumber, apartment.apartmentNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ownerId, address, apartmentNumber);
  }

  public static class Builder {
    private String ownerId;
    private String street;
    private String postalCode;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String country;
    private String description;
    private Map<String, Double> spacesDefinition = new HashMap<>();

    private Builder() {
    }

    public static Builder apartment() {
      return new Builder();
    }

    public Builder withOwnerId(String ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    public Builder withStreet(String street) {
      this.street = street;
      return this;
    }

    public Builder withPostalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder withHouseNumber(String houseNumber) {
      this.houseNumber = houseNumber;
      return this;
    }

    public Builder withApartmentNumber(String apartmentNumber) {
      this.apartmentNumber = apartmentNumber;
      return this;
    }

    public Builder withCity(String city) {
      this.city = city;
      return this;
    }

    public Builder withCountry(String country) {
      this.country = country;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withSpacesDefinition(Map<String, Double> spacesDefinition) {
      this.spacesDefinition = spacesDefinition;
      return this;
    }

    public Apartment build() {
      return new Apartment(ownerId, address(), apartmentNumber, spaces(), description);
    }

    private Address address() {
      return new Address(street, postalCode, houseNumber, city, country);
    }

    private List<Space> spaces() {
      return SpacesFactory.create(spacesDefinition);
    }
  }
}
