package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByBookName(String bookName);
}
