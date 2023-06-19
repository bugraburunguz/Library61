package com.library.service;

import com.library.advice.exceptions.AuthorNotFoundException;
import com.library.advice.exceptions.BookNotFoundException;
import com.library.model.request.BookRequest;
import com.library.model.response.BookResponse;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.persistance.jpa.repository.AuthorRepository;
import com.library.persistance.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookResponse> getAllBooks() {
        List<BookEntity> bookEntityList = bookRepository.findAll();

        return bookEntityList.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBooksByAuthorName(String authorName) {
        List<AuthorEntity> authors = authorRepository.findAllByName(authorName);

        List<BookEntity> bookEntityList = authors.stream()
                .flatMap(author -> bookRepository.findAllByAuthor(author).stream())
                .collect(Collectors.toList());

        return bookEntityList.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    private BookResponse convertToBookResponse(BookEntity bookEntity) {
        return BookResponse.builder()
                .bookName(bookEntity.getTitle())
                .genre(bookEntity.getBookGenre())
                .availability(bookEntity.isAvailability())
                .authorName(bookEntity.getAuthor().getName())
                .build();
    }

    public Long addBook(BookRequest request, Long authorId) {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);
        if (!author.isPresent()) {
            throw new AuthorNotFoundException();
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(request.getTitle());
        bookEntity.setAvailability(request.isAvailability());
        bookEntity.setBookGenre(request.getBookGenre());
        bookEntity.setAuthor(author.get());

        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

    public BookRequest updateBook(Long id, BookRequest book) {
        Optional<BookEntity> existingBook = bookRepository.findById(id);
        if (!existingBook.isPresent()) {
            throw new BookNotFoundException();
        }
        if (Objects.nonNull(book.getTitle())) {
            existingBook.get().setTitle(book.getTitle());
        }
        existingBook.get().setAvailability(book.isAvailability());

        bookRepository.save(existingBook.get());

        return book;

    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }
}