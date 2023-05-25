package com.library.persistance.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String bookName;

    //tür enumlı olsun
    private String type;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private WriterEntity writer;
}
