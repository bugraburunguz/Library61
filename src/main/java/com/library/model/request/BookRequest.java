package com.library.model.request;

import com.library.persistance.jpa.entity.AuthorEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
}