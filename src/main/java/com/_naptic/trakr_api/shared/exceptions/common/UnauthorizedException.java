package com._naptic.trakr_api.shared.exceptions.common;

import com._naptic.trakr_api.shared.exceptions.core.ApiException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
