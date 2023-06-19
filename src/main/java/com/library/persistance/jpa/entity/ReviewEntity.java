package com.library.persistance.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "reviews")
@EntityListeners(AuditingEntityListener.class)
public class ReviewEntity implements Serializable {
    static final long serialVersionUID = 4235637833262722267L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private BookEntity book;

    private int rating; // You can decide the scale. 1 to 5 is common.

    private String comment;

}