package com._naptic.trakr_api.shared.responses;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrappedResponse {
    public static <T> ResponseEntity<CustomApiResponse<T>> of(T data, String message) {
        CustomApiResponse<T> response = CustomApiResponse.of(data, message);

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CustomApiResponse<T>> of(T data, String message, HttpStatus status) {
        CustomApiResponse<T> response = CustomApiResponse.of(data, message);

        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<PaginatedApiResponse<T>> of(Page<T> data, String message) {
        PaginatedApiResponse<T> response = PaginatedApiResponse.of(data, message);

        return ResponseEntity.ok(response);
    }
}
