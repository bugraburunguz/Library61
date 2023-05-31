package com.library.persistance.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.model.enums.BookGenre;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")
    private AuthorEntity author;

    @Column(nullable = false)
    private boolean availability;

    @OneToMany(mappedBy = "book")
    private Set<ReservationEntity> reservations;

    @OneToMany(mappedBy = "book")
    private Set<RentalEntity> rentals;

    @OneToMany(mappedBy = "book")
    private Set<ReviewEntity> reviews;
}