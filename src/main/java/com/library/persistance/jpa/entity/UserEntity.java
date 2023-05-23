package com.library.persistance.jpa.entity;

import com.library.model.enums.UserSegmentType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_info", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_info", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private UserSegmentType userSegmentType;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String mail;

    @Column(unique = true, nullable  = false)
    private String mobileNumber;
}

/*
 * id
 * isim
 * soyisim
 * segmentation
 * password
 * userName
 * number
 * reservationID

 */