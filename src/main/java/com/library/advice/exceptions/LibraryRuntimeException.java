package com.library.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class LibraryRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int code;
    private final HttpStatus status;

    public LibraryRuntimeException(int code, String message) {
        this(BAD_REQUEST, code, message);
    }

    public LibraryRuntimeException(HttpStatus status, int code, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
