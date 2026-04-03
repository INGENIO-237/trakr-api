package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.auth.dtos.ForgotPasswordRequestDto;
import com._naptic.trakr_api.auth.dtos.ResetPasswordDto;
import com._naptic.trakr_api.tokens.services.TokenService;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import com._naptic.trakr_api.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final TokenService tokenService;

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
