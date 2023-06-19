package com.library.service;

import com.library.advice.exceptions.AuthorNotFoundException;
import com.library.converter.AuthorConverter;
import com.library.model.request.AuthorRequest;
import com.library.model.response.AuthorResponse;
import com.library.model.response.BookResponse;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.persistance.jpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorResponse> getAllAuthors() {
        List<AuthorEntity> authorEntityList = authorRepository.findAll();

        return authorEntityList.stream()
                .map(AuthorConverter::convertToAuthorResponse)
                .collect(Collectors.toList());
    }

    public AuthorEntity addAuthor(AuthorRequest authorRequest) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorRequest.getName());
        return authorRepository.save(author);
    }

    public AuthorResponse getAuthorById(Long id) {
        AuthorEntity author = authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);

        return AuthorConverter.convertToAuthorResponse(author);
    }

    public AuthorEntity updateAuthor(Long id, AuthorRequest author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(AuthorNotFoundException::new);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException();
        }
        authorRepository.deleteById(id);
    }
}

