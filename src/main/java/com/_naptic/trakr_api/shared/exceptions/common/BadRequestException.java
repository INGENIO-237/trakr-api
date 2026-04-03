package com._naptic.trakr_api.shared.exceptions.common;

import com.rinoov.api.shared.exceptions.core.ApiException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
    public BadRequestException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
