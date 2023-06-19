package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

     List<AuthorEntity> findAllByName(String name);
}
