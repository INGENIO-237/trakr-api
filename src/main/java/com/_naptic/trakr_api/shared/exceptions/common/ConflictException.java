package com._naptic.trakr_api.shared.exceptions.common;

import com.rinoov.api.shared.exceptions.core.ApiException;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
