package com.endorocket.hexagonalapp.domain.hotel;

import javax.persistence.*;

@Entity
@Table(name = "HOTEL")
public class Hotel {
  @Id
  @GeneratedValue
  private String id;

  private final String name;

  @Embedded
  private final Address address;

  Hotel(String name, Address address) {
    this.name = name;
    this.address = address;
  }
}
