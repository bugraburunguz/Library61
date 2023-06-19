package com.library.advice.exceptions;

import com.library.constant.UserServiceConstant;

import static com.library.advice.constants.ErrorCodes.VALIDATION_ERROR;

public class InvalidPhoneNumberException extends LibraryRuntimeException {

    public InvalidPhoneNumberException() {
        super(VALIDATION_ERROR, UserServiceConstant.INVALID_PHONE_NUMBER);
    }
}
