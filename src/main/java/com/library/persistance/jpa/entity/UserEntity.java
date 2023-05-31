package com.library.persistance.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private Set<ReservationEntity> reservations;

    @OneToMany(mappedBy = "user")
    private Set<RentalEntity> rentals;

    @OneToMany(mappedBy = "user")
    private Set<ReviewEntity> reviews;


}