package com._naptic.trakr_api.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
