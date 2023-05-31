package com.library.service;

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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity addBook(BookRequest bookRequest, Long authorId) {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);
        if(!author.isPresent()){
            //Author bulunamadı exception olarak değiştir
            throw new EntityNotFoundException();
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAvailability(true);
        bookEntity.setAuthor(author.get());
        return bookRepository.save(bookEntity);
    }

    public List<BookEntity> getBooksByAuthorId(Long authorId) {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author with ID " + authorId + " not found");
        }
        AuthorEntity authorEntity = author.get();
        List<BookEntity> books = bookRepository.findAllByAuthor(authorEntity);
        authorEntity.setBooks(books); // Yazar nesnesine kitapları ekleyin
        return books;
    }

    public BookEntity updateBook(Long id, BookEntity book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    //existingBook.setAvailability(book.avai);
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found"));
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }
}