package com.library.controller;


import com.library.persistance.jpa.entity.RentalEntity;
import com.library.service.RentalService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<RentalEntity> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping
    public ResponseEntity<RentalEntity> addRental(@RequestBody RentalEntity rental) {
        RentalEntity savedRental = rentalService.addRental(rental);
        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public RentalEntity getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PutMapping("/{id}")
    public RentalEntity updateRental(@PathVariable Long id, @RequestBody RentalEntity rental) {
        return rentalService.updateRental(id, rental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}