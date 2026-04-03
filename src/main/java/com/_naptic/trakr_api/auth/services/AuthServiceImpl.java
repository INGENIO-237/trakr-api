package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import com._naptic.trakr_api.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserService userService;

    @Override
    public UserResponse register(CreateUserDto dto) {
        return userService.create(dto);
    }
}
