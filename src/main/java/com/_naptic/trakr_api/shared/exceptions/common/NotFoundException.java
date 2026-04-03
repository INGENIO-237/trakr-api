package com._naptic.trakr_api.shared.exceptions.common;

import com._naptic.trakr_api.shared.exceptions.core.ApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
