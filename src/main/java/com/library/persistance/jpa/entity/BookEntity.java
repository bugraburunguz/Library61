package com.library.persistance.jpa.entity;

import com.library.model.enums.BookGenre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Column(nullable = false)
    private boolean availability;

    @Column
    private BookGenre bookGenre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private Set<ReservationEntity> reservations;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private Set<ReviewEntity> reviews;
}