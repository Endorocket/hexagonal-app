package com.endorocket.hexagonalapp.domain.hotel;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "HOTEL")
@SuppressWarnings("PMD.UnusedPrivateField")
public class Hotel {
  @Id
  @GeneratedValue
  private UUID id;

  private final String name;

  @Embedded
  private final Address address;

  Hotel(String name, Address address) {
    this.name = name;
    this.address = address;
  }
}
