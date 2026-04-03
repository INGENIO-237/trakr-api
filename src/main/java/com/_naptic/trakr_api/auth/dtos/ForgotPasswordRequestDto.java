package com._naptic.trakr_api.auth.dtos;

import jakarta.validation.constraints.Email;

public record ForgotPasswordRequestDto(
        @Email(message = "Invalid email address format") String email) {
}
