package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByAuthor(AuthorEntity author);
}
