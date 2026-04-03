package com._naptic.trakr_api.shared.exceptions.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
