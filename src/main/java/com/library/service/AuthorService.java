package com.library.service;

import com.library.model.request.AuthorRequest;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorEntity addAuthor(AuthorRequest authorRequest) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorRequest.getName());
        return authorRepository.save(author);
    }

    public AuthorEntity getAuthorById(Long id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        return author.orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " not found"));
    }

    public AuthorEntity updateAuthor(Long id, AuthorEntity author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " not found"));
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author with ID " + id + " not found");
        }
        authorRepository.deleteById(id);
    }
}

