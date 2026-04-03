package com._naptic.trakr_api.users.dtos;

import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record UpdateUserDto(@Email(message = "Invalid email address format") String email, String fullName, String password) {
}
