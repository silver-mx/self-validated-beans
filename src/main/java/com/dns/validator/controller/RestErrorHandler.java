package com.dns.validator.controller;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        var errorBuilder = ErrorMessage.builder()
                .status(BAD_REQUEST.value())
                .message(exception.getMessage());

        findViolationException(exception).ifPresent(violationException -> errorBuilder.message(violationException.getMessage()));

        return ResponseEntity.badRequest().body(errorBuilder.build());
    }

    private Optional<ConstraintViolationException> findViolationException(Exception exception) {
        if (exception.getCause() instanceof ValueInstantiationException instantiationException &&
                instantiationException.getCause() instanceof ConstraintViolationException violationException) {
            return Optional.of(violationException);
        }

        return Optional.empty();
    }

    @Getter
    @Builder
    public static class ErrorMessage {
        int status;
        String message;
    }

}
