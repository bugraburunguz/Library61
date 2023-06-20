package com.library.service;

import com.library.advice.exceptions.InvalidEmailAddressException;
import com.library.advice.exceptions.InvalidPasswordException;
import com.library.advice.exceptions.InvalidPhoneNumberException;
import com.library.converter.UserConverter;
import com.library.model.request.UserRequest;
import com.library.model.response.UserResponse;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.persistance.jpa.repository.UserRepository;
import com.library.validation.impl.GeneralValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.library.converter.UserConverter.convertToUserResponse;
import static com.library.converter.UserConverter.convertToUserResponseList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntity = userRepository.findAll();
        return convertToUserResponseList(userEntity);
    }

    public void addUser(UserRequest user) {

        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validatePhoneNumber(user.getPhoneNumber());

        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .phoneNumber(user.getPhoneNumber())
                .build();
        userRepository.save(userEntity);
    }

    public UserResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));

        return convertToUserResponse(userEntity);
    }

    public Long getUserByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUsername(userName);

        return userEntity.getId();
    }

    public UserRequest updateUser(Long id, UserRequest user) {
        UserEntity userEntity = userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        return UserConverter.convertToUserRequest(userEntity);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    private void validateEmail(String userEmail) {
        if (!GeneralValidator.emailValidator(userEmail)) {
            throw new InvalidEmailAddressException();
        }
    }

    private void validatePassword(String userPassword) {
        if (!GeneralValidator.passwordValidator(userPassword)) {
            throw new InvalidPasswordException();
        }
    }

    private void validatePhoneNumber(String userPassword) {
        if (!GeneralValidator.mobileValidator(userPassword)) {
            throw new InvalidPhoneNumberException();
        }
    }

}

