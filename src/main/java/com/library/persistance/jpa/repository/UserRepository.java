package com.library.persistance.jpa.repository;

import com.library.persistance.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean findAllByEmail(String email);
    UserEntity findByUsername(String username);
}
