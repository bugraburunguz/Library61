package com.library.converter;

import com.library.model.response.AuthorResponse;
import com.library.persistance.jpa.entity.AuthorEntity;
import com.library.persistance.jpa.entity.BookEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorConverter {

    public static AuthorResponse convertToAuthorResponse(AuthorEntity authorEntity) {
        List<String> bookNames = authorEntity.getBooks().stream()
                .map(BookEntity::getTitle)
                .collect(Collectors.toList());

        return AuthorResponse.builder()
                .authorName(authorEntity.getName())
                .bookNames(String.valueOf(bookNames))
                .build();
    }
}