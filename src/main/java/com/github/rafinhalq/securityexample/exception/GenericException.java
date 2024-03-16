package com.github.rafinhalq.securityexample.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericException extends RuntimeException {
    private final HttpStatus status;

    public GenericException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public GenericException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
