package com.library.persistance.jpa.entity;

import com.library.model.enums.BookGenre;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "book")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_info")
    @SequenceGenerator(name = "seq_book_info", sequenceName = "seq_book_info", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private BookGenre genre;

    @Column(nullable = false)
    private Boolean availability;

    @Column(name = "author_id")
    private Long author;
}
