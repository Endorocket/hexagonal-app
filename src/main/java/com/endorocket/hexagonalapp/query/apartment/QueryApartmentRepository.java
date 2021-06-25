package com.endorocket.hexagonalapp.query.apartment;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class QueryApartmentRepository {
  private final SpringQueryApartmentRepository apartmentRepository;
  private final SpringQueryApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

  public QueryApartmentRepository(SpringQueryApartmentRepository apartmentRepository, SpringQueryApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
    this.apartmentRepository = apartmentRepository;
    this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
  }

  public Iterable<ApartmentReadModel> findAll() {
    return apartmentRepository.findAll();
  }

  public ApartmentDetails findById(String id) {
    Optional<ApartmentReadModel> found = apartmentRepository.findById(UUID.fromString(id));

    if (found.isPresent()) {
      Optional<ApartmentBookingHistoryReadModel> foundHistory = apartmentBookingHistoryRepository.findById(id);

      if (foundHistory.isPresent()) {
        ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = foundHistory.get();
        return ApartmentDetails.withHistory(found.get(), apartmentBookingHistoryReadModel);
      } else {
        return ApartmentDetails.withoutHistory(found.get());
      }
    } else {
      return ApartmentDetails.notExisting();
    }
  }
}
