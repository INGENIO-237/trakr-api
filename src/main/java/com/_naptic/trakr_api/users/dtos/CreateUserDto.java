package com._naptic.trakr_api.users.dtos;

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
public class CreateUserDto {
    @NotBlank(message = "Email must be provided")
    @Email(message = "Invalid email address format")
    private String email;

    @NotBlank(message = "Full name must be provided")
    private String fullName;

    @NotBlank(message = "Password must be provided")
    private String password;
}
