package com.library.model.response;

import com.library.model.enums.BookGenre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorResponse {
    private String bookNames;
    private String authorName;
}

