package com.library.service;

import com.library.model.enums.UserSegmentType;
import com.library.model.request.RegisterRequest;
import com.library.model.response.RegisterResponse;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.persistance.jpa.repository.UserRepository;
import com.library.validation.impl.GeneralValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public RegisterResponse registerUser(RegisterRequest registerRequest, UserSegmentType segmentType) {

        assertUserIsNotExisted(registerRequest.getMobileNumber());
        isEmailValid(registerRequest);
        isMobileValid(registerRequest);

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerRequest.getFirstName());
        userEntity.setLastName(registerRequest.getLastName());
        userEntity.setNickName(registerRequest.getNickName());
        userEntity.setMail(registerRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setMobileNumber(registerRequest.getMobileNumber());
        userEntity.setUserSegmentType(segmentType);
        userRepository.save(userEntity);

        return toRegisterResponse(registerRequest, userEntity);
    }

    public RegisterResponse toRegisterResponse(RegisterRequest registerRequest, UserEntity userEntity) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setEmail(registerRequest.getEmail());
        registerResponse.setFirstName(registerRequest.getFirstName());
        registerResponse.setLastName(registerRequest.getLastName());
        registerResponse.setNickName(registerRequest.getNickName());
        registerResponse.setMobileNumber(registerRequest.getMobileNumber());
        registerResponse.setId(userEntity.getId());
        return registerResponse;
    }

    private void isEmailValid(RegisterRequest registerRequest) {
        if (!GeneralValidator.validateEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email geçerli değil");
        }
    }

    private void isMobileValid(RegisterRequest registerRequest) {
        if (!GeneralValidator.validateMobile(registerRequest.getEmail())) {
            throw new RuntimeException("Email geçerli değil");
        }
    }

    private void assertUserIsNotExisted(String mobileNumber) {
        Optional<UserEntity> userEntity = userRepository.findByMobileNumber(mobileNumber);
        if (userEntity.isPresent()) {
            throw new RuntimeException("ALREADY_USED_MOBILE_NUMBER");
        }
    }
}
