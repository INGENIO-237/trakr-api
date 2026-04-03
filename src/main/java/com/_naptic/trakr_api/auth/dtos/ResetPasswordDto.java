package com._naptic.trakr_api.auth.dtos;

import lombok.Builder;

@Builder
public record ResetPasswordDto(String email, String password) {
}
