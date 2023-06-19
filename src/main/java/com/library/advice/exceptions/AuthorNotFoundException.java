package com.library.advice.exceptions;

import com.library.constant.AuthorServiceConstant;

import static com.library.advice.constants.ErrorCodes.AUTHOR_NOT_FOUND;

public class AuthorNotFoundException extends LibraryRuntimeException {

    public AuthorNotFoundException() {
        super(AUTHOR_NOT_FOUND, AuthorServiceConstant.AUTHOR_NOT_FOUND_EXCEPTION);
    }

}
