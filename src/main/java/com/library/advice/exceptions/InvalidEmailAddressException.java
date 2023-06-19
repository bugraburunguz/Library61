package com.library.advice.exceptions;

import com.library.constant.BookServiceConstant;
import com.library.constant.UserServiceConstant;

import static com.library.advice.constants.ErrorCodes.BOOK_NOT_FOUND;
import static com.library.advice.constants.ErrorCodes.VALIDATION_ERROR;

public class InvalidEmailAddressException extends LibraryRuntimeException {

    public InvalidEmailAddressException() {
        super(VALIDATION_ERROR, UserServiceConstant.INVALID_EMAIL_ADDRESS);
    }
}
