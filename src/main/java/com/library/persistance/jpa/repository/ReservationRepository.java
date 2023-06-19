package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
