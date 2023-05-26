package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findAllByFirstNameOrLastName(String authorFirstName, String authorLastName);
}
