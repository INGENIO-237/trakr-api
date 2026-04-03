package com._naptic.trakr_api.tokens;

import com._naptic.trakr_api.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenHolder, String> {
    Optional<TokenHolder> findByUser(User user);

    Optional<TokenHolder> findByToken(String token);
}
