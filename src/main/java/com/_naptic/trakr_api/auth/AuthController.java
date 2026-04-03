package com._naptic.trakr_api.auth;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    @Operation(summary = "Register a user")
    public ResponseEntity<CustomApiResponse<UserResponse>> register(@Valid @RequestBody CreateUserDto dto) {
        UserResponse response = service.register(dto);

        return WrappedResponse.of(response, "User registered successfully");
    }

}
