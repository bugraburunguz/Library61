package com.library.controller;

import com.library.model.request.BookRequest;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public BookEntity addBook(@RequestBody BookRequest book,@RequestParam Long authorId) {
        return bookService.addBook(book, authorId);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookEntity>> getBooksByAuthorId(@PathVariable Long id) {
        List<BookEntity> books = bookService.getBooksByAuthorId(id);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}