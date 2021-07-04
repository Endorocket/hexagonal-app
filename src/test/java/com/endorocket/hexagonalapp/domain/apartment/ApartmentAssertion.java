package com.endorocket.hexagonalapp.domain.apartment;

import com.endorocket.hexagonalapp.domain.space.Space;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ApartmentAssertion {
	private final Apartment actual;

	private ApartmentAssertion(Apartment actual) {
		this.actual = actual;
	}

	public static ApartmentAssertion assertThat(Apartment actual) {
		return new ApartmentAssertion(actual);
	}

	public ApartmentAssertion hasOwnerIdEqualTo(String ownerId) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", ownerId);
		return this;
	}

	public ApartmentAssertion hasDescriptionEqualTo(String description) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", description);
		return this;
	}

	public ApartmentAssertion hasApartmentNumberEqualTo(String apartmentNumber) {
		Assertions.assertThat(actual).hasFieldOrPropertyWithValue("apartmentNumber", apartmentNumber);
		return this;
	}

	public ApartmentAssertion hasAddressEqualTo(String street, String postalCode, String houseNumber, String city, String country) {
		Assertions.assertThat(actual).extracting("address")
			.hasFieldOrPropertyWithValue("street", street)
			.hasFieldOrPropertyWithValue("postalCode", postalCode)
			.hasFieldOrPropertyWithValue("buildingNumber", houseNumber)
			.hasFieldOrPropertyWithValue("city", city)
			.hasFieldOrPropertyWithValue("country", country);
		return this;
	}

	@SuppressWarnings("unchecked")
	public ApartmentAssertion hasSpacesEqualTo(Map<String, Double> spacesDefinition) {
		Assertions.assertThat(actual).extracting("spaces").satisfies(spacesActual -> {
			List<Space> spaces = (List<Space>) spacesActual;
			Assertions.assertThat(spaces).hasSize(spacesDefinition.size());

			spacesDefinition.forEach((name, squareMeter) ->
				Assertions.assertThat(spaces).anySatisfy(hasSpaceThat(name, squareMeter)));
		});
		return this;
	}

	private Consumer<Space> hasSpaceThat(String name, Double squareMeter) {
		return room -> Assertions.assertThat(room)
			.hasFieldOrPropertyWithValue("name", name)
			.hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
	}
}
