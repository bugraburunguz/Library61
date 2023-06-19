package com.library.persistance.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "reservations")
@EntityListeners(AuditingEntityListener.class)
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private BookEntity book;

    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}