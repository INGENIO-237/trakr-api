package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.IDGenerator;
import com._naptic.trakr_api.shared.exceptions.common.ConflictException;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.UserMapper;
import com._naptic.trakr_api.users.UserRepository;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl service;

    private final Faker faker = new Faker();

    @Test
    void shouldCreateUser() {
        CreateUserDto dto = CreateUserDto.builder()
                .email(faker.internet().emailAddress())
                .fullName(faker.name().fullName())
                .password(faker.internet().password())
                .build();

        User entity = User.builder()
                .email(dto.email())
                .password(dto.password())
                .fullName(dto.fullName())
                .build();

        User savedUser = User.builder()
                .id(getId())
                .email(dto.email())
                .fullName(dto.fullName())
                .password(dto.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(mapper.toEntity(any(CreateUserDto.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(savedUser);
        when(mapper.toResponse(any(User.class))).thenReturn(userResponse);

        UserResponse response = service.create(dto);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());
        assertEquals(response.getEmail(), dto.email());
        assertEquals(response.getFullName(), dto.fullName());

    }

    @Test
    void shouldThrowConflictException() {
        CreateUserDto dto = CreateUserDto.builder()
                .email(faker.internet().emailAddress())
                .fullName(faker.name().fullName())
                .password(faker.internet().password())
                .build();

        User existingUser = User.builder()
                .id(getId())
                .email(dto.email())
                .fullName(dto.fullName())
                .password(dto.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));

        ConflictException exception = assertThrows(ConflictException.class, () -> service.create(dto));

        assertEquals("Email address already in use", exception.getMessage());
    }

    private String getId() {
        return IDGenerator.generate("usr");
    }
}