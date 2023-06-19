package com.library.advice.exceptions;

import com.library.constant.UserServiceConstant;

import static com.library.advice.constants.ErrorCodes.VALIDATION_ERROR;

public class UserNotFoundException extends LibraryRuntimeException {

    public UserNotFoundException() {
        super(VALIDATION_ERROR, UserServiceConstant.USER_NOT_FOUND);
    }
}