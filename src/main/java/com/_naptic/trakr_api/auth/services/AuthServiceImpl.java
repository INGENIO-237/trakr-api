package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.auth.dtos.ForgotPasswordRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginResponse;
import com._naptic.trakr_api.auth.dtos.ResetPasswordDto;
import com._naptic.trakr_api.tokens.services.TokenService;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import com._naptic.trakr_api.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequestDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        String accessToken = jwtService.generateToken(dto.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public UserResponse register(CreateUserDto dto) {
        return userService.create(dto);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequestDto dto) {
        User user = userService.getExistingUserByEmail(dto.email());

        tokenService.generate(user);
    }

    @Override
    public void resetPassword(String token, ResetPasswordDto dto) {
        User user = userService.getExistingUserByEmail(dto.email());

        tokenService.validate(user, token);

        UpdateUserDto update = UpdateUserDto.builder()
                .password(dto.password())
                .build();

        userService.update(user.getId(), update);
    }
}
