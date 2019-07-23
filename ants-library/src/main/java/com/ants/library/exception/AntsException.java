package com.ants.library.exception;

import lombok.Getter;

@Getter
public class AntsException extends RuntimeException{
    private final StatusCode statusCode;
    private final String message;

    public AntsException() {
        this.statusCode = StatusCode.FAIL;
        this.message = StatusCode.FAIL.getMessage();
    }

    public AntsException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
        this.message = statusCode.getMessage();
    }

    public AntsException(String message) {
        super(message);
        this.statusCode = StatusCode.FAIL;
        this.message = message;
    }

    public AntsException(StatusCode statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
        this.message = cause.getMessage();
    }

    public AntsException(StatusCode statusCode, String message, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
        this.message = message;
    }
}
