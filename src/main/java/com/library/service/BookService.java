package com.library.service;

import com.library.model.enums.BookGenre;
import com.library.model.request.BookRequest;
import com.library.model.response.BookResponse;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.persistance.jpa.repository.AuthorRepository;
import com.library.persistance.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookRequest createBook(BookRequest bookRequest, BookGenre bookGenre) {
        BookEntity book = new BookEntity();
        book.setBookName(bookRequest.getBookName());
        book.setAvailability(true);
        book.setGenre(bookGenre);

        Optional<AuthorEntity> authorOptional = authorRepository.findById(bookRequest.getAuthorId());
        if (authorOptional.isPresent()) {
            AuthorEntity author = authorOptional.get();
            book.setAuthor(author.getId());
        } else {
            throw new IllegalArgumentException("Yazar bulunamadı.");
        }
        bookRepository.save(book);
        return bookRequest;
    }

    public List<BookResponse> getBookByName(String bookName) {
        List<BookEntity> books = bookRepository.findAllByBookName(bookName);

        return toBookResponse(books);
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookResponse> toBookResponse(List<BookEntity> bookEntities) {
        List<BookResponse> bookResponses = new ArrayList<>();

        for (BookEntity bookEntity : bookEntities) {
            Optional<AuthorEntity> author = authorRepository.findById(bookEntity.getAuthor()); // Kitabın yazarını al

            BookResponse bookResponse = BookResponse.builder()
                    .bookName(bookEntity.getBookName())
                    .availability(bookEntity.getAvailability())
                    .genre(bookEntity.getGenre())
                    .authorName(author.get().getFirstName())
                    .authorLastName(author.get().getLastName())
                    .authorId(author.get().getId())
                    .build();

            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }
}
