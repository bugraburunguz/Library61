package com.library.service;

import com.library.model.enums.UserSegmentType;
import com.library.model.request.RegisterRequest;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.persistance.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public Long registerUser(RegisterRequest registerRequest, UserSegmentType segmentType) {

        assertUserIsNotExisted(registerRequest.getMobileNumber());

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerRequest.getFirstName());
        userEntity.setLastName(registerRequest.getLastName());
        userEntity.setNickName(registerRequest.getNickName());
        userEntity.setMail(registerRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setMobileNumber(registerRequest.getMobileNumber());
        userEntity.setUserSegmentType(segmentType);
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    private void assertUserIsNotExisted(String mobileNumber) {
        Optional<UserEntity> userEntity = userRepository.findByMobileNumber(mobileNumber);
        if (userEntity.isPresent()) {
            throw new RuntimeException("ALREADY_USED_MOBILE_NUMBER");
        }
    }
}
