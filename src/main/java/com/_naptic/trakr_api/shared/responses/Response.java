package com._naptic.trakr_api.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder(builderMethodName = "responseBuilder")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    @Builder.Default
    private HttpStatus status = HttpStatus.OK;
}
