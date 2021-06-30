package com.endorocket.hexagonalapp.infrastructure.rest.api.apartment;

import com.endorocket.hexagonalapp.application.apartment.ApartmentApplicationService;
import com.endorocket.hexagonalapp.application.apartment.ApartmentBookingDto;
import com.endorocket.hexagonalapp.application.apartment.ApartmentDto;
import com.endorocket.hexagonalapp.query.apartment.ApartmentDetails;
import com.endorocket.hexagonalapp.query.apartment.ApartmentReadModel;
import com.endorocket.hexagonalapp.query.apartment.QueryApartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/apartment")
public class ApartmentRestController {
  private final ApartmentApplicationService apartmentApplicationService;
  private final QueryApartmentRepository queryApartmentRepository;

  public ApartmentRestController(ApartmentApplicationService apartmentApplicationService, QueryApartmentRepository queryApartmentRepository) {
    this.apartmentApplicationService = apartmentApplicationService;
    this.queryApartmentRepository = queryApartmentRepository;
  }

  @PostMapping
  public ResponseEntity<String> add(@RequestBody ApartmentDto apartmentDto) {
    String id = apartmentApplicationService.add(apartmentDto);

    return ResponseEntity.created(URI.create("/apartment/" + id)).build();
  }

  @PutMapping("/book/{id}")
  public void book(@PathVariable String id, @RequestBody ApartmentBookingDto apartmentBookingDto) {
    apartmentApplicationService.book(id, apartmentBookingDto);
  }

  @GetMapping
  public Iterable<ApartmentReadModel> findAll() {
    return queryApartmentRepository.findAll();
  }

  @GetMapping("/{id}")
  public ApartmentDetails findById(@PathVariable String id) {
    return queryApartmentRepository.findById(id);
  }
}
