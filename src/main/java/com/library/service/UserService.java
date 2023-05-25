package com.library.service;

import com.library.model.request.RegisterRequest;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.persistance.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    /*
    user service RUD işlemleri
     */
    private final UserRepository userRepository;

    public void updateUser(Long userId, RegisterRequest registerRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        if (Objects.nonNull(registerRequest.getEmail())) {
            user.setMail(registerRequest.getEmail());
        }
        /*
        devamı yazılacak
         */
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserEntity getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        return user;
    }
}
