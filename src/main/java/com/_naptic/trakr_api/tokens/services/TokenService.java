package com._naptic.trakr_api.tokens.services;

import com._naptic.trakr_api.users.User;

import java.security.NoSuchAlgorithmException;

public interface TokenService {
    void generate(User user);

    void validate(User user, String token);
}
