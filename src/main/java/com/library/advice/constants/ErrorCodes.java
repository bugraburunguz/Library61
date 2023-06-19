package com.library.advice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {

    public static final int BOOK_NOT_FOUND = 1001;
    public static final int AUTHOR_NOT_FOUND = 1001;

    // This must be at the end
    public static final int VALIDATION_ERROR = 1998;
    public static final int UNKNOWN_ERROR = 1999;
}