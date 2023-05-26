package com.library.model.response;

import com.library.model.enums.BookGenre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookResponse {
    private String bookName;
    private BookGenre genre;
    private Boolean availability;
    private String authorName;
    private String authorLastName;
    private Long authorId;

}
