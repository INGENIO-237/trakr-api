package com._naptic.trakr_api.tokens.services;

import com._naptic.trakr_api.IDGenerator;
import com._naptic.trakr_api.shared.exceptions.common.BadRequestException;
import com._naptic.trakr_api.tokens.TokenHolder;
import com._naptic.trakr_api.tokens.TokenRepository;
import com._naptic.trakr_api.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {
    private final int VALIDITY_MINUTES = 30;
    @Mock
    private TokenRepository repository;
    @Mock
    private ApplicationEventPublisher publisher;
    @InjectMocks
    private TokenServiceImpl service;

    @Test
    void shouldValidateToken() {
        User user = User.builder()
                .id(IDGenerator.generate("usr"))
                .build();

        TokenHolder tokenHolder = TokenHolder.builder()
                .user(user)
                .token(getToken())
                .expiresAt(LocalDateTime.now().plusMinutes(VALIDITY_MINUTES))
                .build();

        when(repository.findByUser(any(User.class))).thenReturn(Optional.of(tokenHolder));

        assertDoesNotThrow(() -> service.validate(user, tokenHolder.getToken()));
    }

    @Test
    void shouldThrowOnInexistentTokenHolder() {
        User user = User.builder()
                .id(IDGenerator.generate("usr"))
                .build();

        when(repository.findByUser(any(User.class))).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.validate(user, "some-token-string"));

        assertEquals("Invalid token. Please, request a new one.", exception.getMessage());
    }

    @Test
    void shouldThrowBadRequestOnIncorrectToken() {
        User user = User.builder()
                .id(IDGenerator.generate("usr"))
                .build();

        String token = getToken();

        TokenHolder tokenHolder = TokenHolder.builder()
                .user(user)
                .token(getToken())
                .expiresAt(LocalDateTime.now().plusMinutes(VALIDITY_MINUTES))
                .build();

        when(repository.findByUser(any(User.class))).thenReturn(Optional.of(tokenHolder));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.validate(user, token));

        assertEquals("Invalid token", exception.getMessage());
    }

    @Test
    void shouldThrowBadRequestOnExpiredToken() {
        User user = User.builder()
                .id(IDGenerator.generate("usr"))
                .build();

        String token = getToken();

        TokenHolder tokenHolder = TokenHolder.builder()
                .user(user)
                .token(token)
                .expiresAt(LocalDateTime.now().minusMinutes(VALIDITY_MINUTES))
                .build();

        when(repository.findByUser(any(User.class))).thenReturn(Optional.of(tokenHolder));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.validate(user, token));

        assertEquals("The token has expired. Please, request a new one.", exception.getMessage());
    }

    private String getId() {
        return IDGenerator.generate("tkn");
    }

    private String getToken() {
        return TokenHolder.ID_PREFIX + "." + UUID.randomUUID().toString();
    }
}