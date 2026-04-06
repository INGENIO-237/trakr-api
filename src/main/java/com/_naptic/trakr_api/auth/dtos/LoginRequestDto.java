package com._naptic.trakr_api.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address format provided")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
