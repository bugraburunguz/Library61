package com.library.controller;

import com.library.model.enums.BookGenre;
import com.library.model.request.BookRequest;
import com.library.model.response.BookResponse;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Long addBook(@RequestBody BookRequest book) {
        return bookService.addBook(book);
    }

    @GetMapping("/books")
    public List<BookResponse> getBooksByAuthorName(@RequestParam String name) {
        return bookService.getBooksByAuthorName(name);
    }

    @PutMapping("/{id}")
    public BookRequest updateBook(@PathVariable Long id, @RequestBody BookRequest book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}