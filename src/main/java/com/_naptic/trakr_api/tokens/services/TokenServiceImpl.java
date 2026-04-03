package com._naptic.trakr_api.tokens.services;

import com._naptic.trakr_api.shared.exceptions.common.BadRequestException;
import com._naptic.trakr_api.tokens.TokenHolder;
import com._naptic.trakr_api.tokens.TokenRepository;
import com._naptic.trakr_api.tokens.events.ResetPasswordTokenCreated;
import com._naptic.trakr_api.users.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository repository;
    private final ApplicationEventPublisher publisher;
    private final int VALIDITY_MINUTES = 30;

    @Override
    public void generate(User user) {
        Optional<TokenHolder> existingTokenHolder = repository.findByUser(user);

        String token = TokenHolder.ID_PREFIX + "." + UUID.randomUUID().toString();

        TokenHolder tokenHolder = existingTokenHolder.orElseGet(() -> TokenHolder.builder().user(user).build());

        tokenHolder.setToken(token);
        tokenHolder.setExpiresAt(LocalDateTime.now().plusMinutes(VALIDITY_MINUTES));

        repository.save(tokenHolder);

        publisher.publishEvent(new ResetPasswordTokenCreated(user, token));
    }

    @Override
    public void validate(User user, String token) {
        Optional<TokenHolder> existingTokenHolder = repository.findByUser(user);

        if (existingTokenHolder.isEmpty()) {
            throw new BadRequestException("Invalid token. Please, request a new one.");
        }

        TokenHolder tokenHolder = existingTokenHolder.get();

        if (!tokenHolder.getToken().equals(token)) {
            throw new BadRequestException("Invalid token");
        }

        boolean expired = tokenHolder.getExpiresAt().isBefore(LocalDateTime.now());

        if (expired) {
            throw new BadRequestException("The token has expired. Please, request a new one.");
        }

        tokenHolder.setExpiresAt(LocalDateTime.now().minusMinutes(VALIDITY_MINUTES)); // Invalidate token after validated
    }
}
