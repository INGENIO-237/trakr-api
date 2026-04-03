package com._naptic.trakr_api.shared.exceptions.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<String> errors; 
}
