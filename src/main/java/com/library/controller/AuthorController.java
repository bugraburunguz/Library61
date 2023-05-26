package com.library.controller;

import com.library.model.request.AuthorRequest;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/writers")
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Create Author")
    @PostMapping
    public ResponseEntity<AuthorRequest> createAuthor(@RequestBody @Validated AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.createAuthor(authorRequest));
    }

    @Operation(summary = "Get Author by Name")
    @GetMapping
    public ResponseEntity<Optional<AuthorEntity>> getAuthorByWriter(@Parameter String authorFirstName,
                                                                    @Parameter String authorLastName) {
        return ResponseEntity.ok(authorService.getByAuthorName(authorFirstName, authorLastName));
    }

    @Operation(summary = "Delete User by Id")
    @DeleteMapping("/delete")
    public void deleteUserById(@RequestParam @Validated Long authorId) {
        authorService.deleteAuthorById(authorId);
    }

}
