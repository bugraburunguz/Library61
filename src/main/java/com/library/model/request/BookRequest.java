package com.library.model.request;

import com.library.model.enums.BookGenre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private boolean availability;
    private BookGenre bookGenre;
}