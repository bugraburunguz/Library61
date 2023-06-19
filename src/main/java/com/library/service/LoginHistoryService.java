package com.library.service;

import com.library.persistance.jpa.entity.LoginHistoryEntity;
import com.library.persistance.jpa.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginHistoryService {
    private final LoginHistoryRepository loginHistoryRepository;

    public void addLoginHistory(String username, boolean success) {
        LoginHistoryEntity loginHistory = new LoginHistoryEntity();
        loginHistory.setUsername(username);
        loginHistory.setLoginTime(LocalDateTime.now());
        loginHistory.setSuccess(success);
        loginHistoryRepository.save(loginHistory);
    }
}
