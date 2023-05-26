package com.library.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String bookName;
    private Long authorId;
}