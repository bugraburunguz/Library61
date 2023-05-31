package com.library.service;

import com.library.persistance.jpa.entity.RentalEntity;
import com.library.persistance.jpa.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<RentalEntity> getAllRentals() {
        return rentalRepository.findAll();
    }

    public RentalEntity addRental(RentalEntity rental) {
        return rentalRepository.save(rental);
    }

    public RentalEntity getRentalById(Long id) {
        Optional<RentalEntity> rental = rentalRepository.findById(id);
        return rental.orElseThrow(() -> new EntityNotFoundException("Rental with ID " + id + " not found"));
    }

    public RentalEntity updateRental(Long id, RentalEntity rental) {
        return rentalRepository.findById(id)
                .map(existingRental -> {
                    existingRental.setUser(rental.getUser());
                    existingRental.setBook(rental.getBook());
                    existingRental.setRentalStartDate(rental.getRentalStartDate());
                    existingRental.setRentalEndDate(rental.getRentalEndDate());
                    return rentalRepository.save(rental);
                })
                .orElseThrow(() -> new EntityNotFoundException("Rental with ID " + id + " not found"));
    }

    public void deleteRental(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new EntityNotFoundException("Rental with ID " + id + " not found");
        }
        rentalRepository.deleteById(id);
    }
}