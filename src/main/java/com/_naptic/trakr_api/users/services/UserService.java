package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;

public interface UserService {
    UserResponse create(CreateUserDto dto);
}
