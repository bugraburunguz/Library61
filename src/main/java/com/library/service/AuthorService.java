package com.library.service;

import com.library.model.request.AuthorRequest;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorRequest createAuthor(AuthorRequest authorRequest) {
        AuthorEntity author = new AuthorEntity();
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());

        authorRepository.save(author);
        return authorRequest;
    }

    public Optional<AuthorEntity> getByAuthorName(String firstName, String lastName) {
        return authorRepository.findAllByFirstNameOrLastName(firstName, lastName);
    }

    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}

