package com.library.persistance.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "writer")
public class WriterEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany
    @JoinColumn(name = "book_id")
    List<BookEntity> bookList;
}
/*
 * Id
 * Name
 * Surname
 * Books
 */