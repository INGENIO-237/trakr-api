package com._naptic.trakr_api.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomApiResponse<T> extends Response {
    private T data;

    public static <T> CustomApiResponse<T> of(T data, String message) {
        return CustomApiResponse.<T>builder()
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CustomApiResponse<T> of(T data, String message, HttpStatus status) {
        return CustomApiResponse.<T>builder()
                .data(data)
                .message(message)
                .status(status)
                .build();
    }
}
