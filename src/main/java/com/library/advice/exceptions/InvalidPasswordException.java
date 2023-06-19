package com.library.advice.exceptions;

import com.library.constant.UserServiceConstant;

import static com.library.advice.constants.ErrorCodes.VALIDATION_ERROR;

public class InvalidPasswordException  extends LibraryRuntimeException {

    public InvalidPasswordException() {
        super(VALIDATION_ERROR, UserServiceConstant.INVALID_PASSWORD);
    }
}
