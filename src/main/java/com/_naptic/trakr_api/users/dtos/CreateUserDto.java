package com._naptic.trakr_api.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateUserDto(
        @NotBlank(message = "Email must be provided") @Email(message = "Invalid email address format") String email,

        @NotBlank(message = "Full name must be provided") String fullName,

        @NotBlank(message = "Password must be provided") String password) {
}
