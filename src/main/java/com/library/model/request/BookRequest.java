package com.library.model.request;

import com.library.model.enums.BookGenre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private Long authorId;
    private String title;
    private boolean availability;
    private String bookGenre;
}