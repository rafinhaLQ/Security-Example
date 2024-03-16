package com.github.rafinhalq.securityexample.exception.handler;

import com.github.rafinhalq.securityexample.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Object> handleGenericException(GenericException ex) {
        var errorDto = createResponse(ex, ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        var errorDto = createResponse(ex, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var object = Objects.requireNonNull(ex.getTarget()).getClass().getName();
        var errors = ex.getFieldErrors().stream()
            .map(error -> String.format("[%s: %s]", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.joining(" - "));
        var message = String.format("Invalid %s - %s", object, errors);
        var errorDto = createResponse(ex, message);
        return ResponseEntity.status(ex.getStatusCode()).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        var errorDto = createResponse(ex, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    private Map<String, Object> createResponse(Exception e, String message) {
        log.error("An exception {} occurred", e.getClass().getSimpleName(), e);

        var errorDto = new HashMap<String, Object>();
        errorDto.put("error", e.getClass().getSimpleName());
        errorDto.put("message", message);

        return errorDto;
    }
}
