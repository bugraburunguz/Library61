package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}
