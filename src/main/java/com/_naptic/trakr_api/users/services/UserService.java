package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;

public interface UserService {
    UserResponse create(CreateUserDto dto);

    UserResponse update(String userId, UpdateUserDto dto);

    User getExistingUser(String userId);

    User getExistingUserByEmail(String email);

    void ensureEmailNotInUse(String email);
}
