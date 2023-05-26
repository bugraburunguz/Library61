package com.library.controller;

import com.library.model.enums.BookGenre;
import com.library.model.request.BookRequest;
import com.library.model.response.BookResponse;
import com.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Create Book")
    @PostMapping
    public BookRequest createBook(@RequestHeader BookGenre bookGenre, @RequestBody @Validated BookRequest bookRequest) {
        return bookService.createBook(bookRequest, bookGenre);
    }

    @Operation(summary = "Get Book by Name")
    @GetMapping
    public List<BookResponse> getAuthorByWriter(@RequestParam String bookName) {
        return bookService.getBookByName(bookName);
    }


    @Operation(summary = "Delete User by Id")
    @DeleteMapping("/delete")
    public void deleteUserById(@RequestParam @Validated Long bookId) {
        bookService.deleteBookById(bookId);
    }
}
