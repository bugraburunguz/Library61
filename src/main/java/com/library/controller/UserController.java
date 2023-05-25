package com.library.controller;

import com.library.model.response.RegisterResponse;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get User by Id")
    @GetMapping
    public ResponseEntity<UserEntity> getUserById(@RequestParam Long userId) {
     return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Delete User by Id")
    @DeleteMapping("/delete")
    public void deleteUserById(@RequestParam @Validated Long userId) {
        userService.deleteUserById(userId);
    }
}
