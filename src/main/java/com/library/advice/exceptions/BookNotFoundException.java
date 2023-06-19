package com.library.advice.exceptions;

import com.library.constant.BookServiceConstant;

import static com.library.advice.constants.ErrorCodes.BOOK_NOT_FOUND;
import static com.library.advice.constants.ErrorCodes.VALIDATION_ERROR;

public class BookNotFoundException extends LibraryRuntimeException {

    public BookNotFoundException() {
        super(VALIDATION_ERROR, BookServiceConstant.BOOK_NOT_FOUND_EXCEPTION);
    }
}
