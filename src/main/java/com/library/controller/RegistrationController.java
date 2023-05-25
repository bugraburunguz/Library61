package com.library.controller;

import com.library.model.enums.UserSegmentType;
import com.library.model.request.RegisterRequest;
import com.library.model.response.RegisterResponse;
import com.library.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestHeader UserSegmentType segmentType,
                                         @RequestBody @Validated RegisterRequest registerRequest) {
        return registrationService.registerUser(registerRequest, segmentType);
    }
}
