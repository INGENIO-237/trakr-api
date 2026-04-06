package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.auth.dtos.ForgotPasswordRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginRequestDto;
import com._naptic.trakr_api.auth.dtos.LoginResponse;
import com._naptic.trakr_api.auth.dtos.ResetPasswordDto;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;

public interface AuthService {
    LoginResponse login(LoginRequestDto dto);

    UserResponse register(CreateUserDto dto);

    void forgotPassword(ForgotPasswordRequestDto dto);

    void resetPassword(String token, ResetPasswordDto dto);
}
