package com.library.persistance.jpa.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {

    static final long serialVersionUID = 4235637833262722277L;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Set<ReservationEntity> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<ReviewEntity> reviews;


}