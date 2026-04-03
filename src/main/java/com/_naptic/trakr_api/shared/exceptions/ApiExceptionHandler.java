package com._naptic.trakr_api.shared.exceptions;

import com.rinoov.api.shared.exceptions.core.ApiException;
import com.rinoov.api.shared.exceptions.core.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(ExceptionResponse.builder()
                .errors(errors)
                .message("Validation Errors")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiExceptions(ApiException exception) {
        String message = String.format("An error occurred: %s", exception.getMessage());

        logger.warn(message);

        return ResponseEntity.status(exception.getStatus()).body(ExceptionResponse.builder()
                .message(exception.getMessage())
                .status(exception.getStatus())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .message("Internal Server Error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
