package com._naptic.trakr_api.auth;

import com._naptic.trakr_api.auth.dtos.ForgotPasswordRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginResponse;
import com._naptic.trakr_api.auth.dtos.ResetPasswordDto;
import com._naptic.trakr_api.auth.services.AuthService;
import com._naptic.trakr_api.shared.responses.CustomApiResponse;
import com._naptic.trakr_api.shared.responses.WrappedResponse;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    @Operation(summary = "Log in a user")
    public ResponseEntity<CustomApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequestDto dto) {
        LoginResponse response = service.login(dto);

        return WrappedResponse.of(response, "User logged in successfully");
    }

    @PostMapping("/register")
    @Operation(summary = "Register a user")
    public ResponseEntity<CustomApiResponse<UserResponse>> register(@Valid @RequestBody CreateUserDto dto) {
        UserResponse response = service.register(dto);

        return WrappedResponse.of(response, "User registered successfully");
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Request a link to reset a forgotten password")
    public ResponseEntity<CustomApiResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto dto) {
        service.forgotPassword(dto);

        return WrappedResponse.of((Void) null, "Password reset link sent successfully");
    }

    @PostMapping("/reset-password/{token}")
    @Operation(summary = "Reset a forgotten password")
    public ResponseEntity<CustomApiResponse<Void>> resetPassword(@PathVariable String token, @Valid @RequestBody ResetPasswordDto dto) {
        service.resetPassword(token, dto);

        return WrappedResponse.of((Void) null, "Password reset successfully");
    }

}
