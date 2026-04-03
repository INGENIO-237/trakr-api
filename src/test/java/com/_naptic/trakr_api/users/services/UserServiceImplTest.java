package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.IDGenerator;
import com._naptic.trakr_api.shared.exceptions.common.ConflictException;
import com._naptic.trakr_api.shared.exceptions.common.NotFoundException;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.UserMapper;
import com._naptic.trakr_api.users.UserRepository;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
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
    private final Faker faker = new Faker();
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldCreateUser() {
        CreateUserDto dto = CreateUserDto.builder().email(faker.internet().emailAddress()).fullName(faker.name().fullName()).password(faker.internet().password()).build();

        User entity = User.builder().email(dto.email()).password(dto.password()).fullName(dto.fullName()).build();

        User savedUser = User.builder().id(getId()).email(dto.email()).fullName(dto.fullName()).password(dto.password()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        UserResponse userResponse = UserResponse.builder().id(savedUser.getId()).email(savedUser.getEmail()).fullName(savedUser.getFullName()).createdAt(savedUser.getCreatedAt()).updatedAt(savedUser.getUpdatedAt()).build();

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
        CreateUserDto dto = CreateUserDto.builder().email(faker.internet().emailAddress()).fullName(faker.name().fullName()).password(faker.internet().password()).build();

        User existingUser = User.builder().id(getId()).email(dto.email()).fullName(dto.fullName()).password(dto.password()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));

        ConflictException exception = assertThrows(ConflictException.class, () -> service.create(dto));

        assertEquals("Email address already in use", exception.getMessage());
    }

    @Test
    void shouldGetExistingUser() {
        User user = User.builder().id(getId()).build();

        when(repository.findById(anyString())).thenReturn(Optional.of(user));

        User response = service.getExistingUser(user.getId());

        assertNotNull(response);
        assertEquals(response.getId(), user.getId());
    }

    @Test
    void shouldThrowNotFoundUser() {
        String userId = getId();

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.getExistingUser(userId));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void shouldGetExistingUserByEmail() {
        User user = User.builder().email(faker.internet().emailAddress()).build();

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(user));

        User response = service.getExistingUserByEmail(user.getEmail());

        assertNotNull(response);
        assertEquals(response.getEmail(), user.getEmail());
    }

    @Test
    void shouldThrowNotFoundUserByEmail() {
        String email = faker.internet().emailAddress();

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.getExistingUserByEmail(email));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void shouldUpdateUser() {
        UpdateUserDto dto = UpdateUserDto.builder().email(faker.internet().emailAddress()).fullName(faker.name().fullName()).build();

        User existingUser = User.builder().id(getId()).fullName(faker.name().fullName()).email(faker.internet().emailAddress()).build();

        User updatedUser = User.builder().id(existingUser.getId()).email(dto.email()).fullName(dto.fullName()).build();

        UserResponse userResponse = UserResponse.builder().id(existingUser.getId()).email(dto.email()).fullName(dto.fullName()).build();

        when(repository.findById(anyString())).thenReturn(Optional.of(existingUser));
        when(mapper.toResponse(any(User.class))).thenReturn(userResponse);
        when(repository.save(any(User.class))).thenReturn(updatedUser);

        UserResponse response = service.update(existingUser.getId(), dto);

        assertNotNull(response);
        assertEquals(response.getEmail(), dto.email());
        assertEquals(response.getFullName(), dto.fullName());
        assertNotEquals(response.getEmail(), existingUser.getEmail());
        assertNotEquals(response.getFullName(), existingUser.getFullName());
    }

    @Test
    void shouldThrowConflictingEmailExceptionAndNotUpdateUser() {
        User conflictUser = User.builder().id(getId()).email(faker.internet().emailAddress()).build();
        User existingUser = User.builder().id(getId()).email(faker.internet().emailAddress()).build();
        UpdateUserDto dto = UpdateUserDto.builder().email(conflictUser.getEmail()).build();


        when(repository.findById(anyString())).thenReturn(Optional.of(existingUser));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(conflictUser));

        ConflictException exception = assertThrows(ConflictException.class, () -> service.update(existingUser.getId(), dto));

        assertEquals("Email address already in use", exception.getMessage());
    }

    @Test
    void shouldThrowNotFoundUserExceptionAndNotUpdateUser() {
        UpdateUserDto dto = UpdateUserDto.builder().email(faker.internet().emailAddress()).build();

        when(repository.findById(anyString())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(getId(), dto));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void shouldThrowConflictingEmail() {
        String email = faker.internet().emailAddress();

        User conflictUser = User.builder()
                .email(email)
                .build();

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(conflictUser));

        ConflictException exception = assertThrows(ConflictException.class, () -> service.ensureEmailNotInUse(email));

        assertEquals("Email address already in use", exception.getMessage());
    }

    @Test
    void shouldNotThrowConflictingEmail() {
        String email = faker.internet().emailAddress();

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> service.ensureEmailNotInUse(email));
    }

    private String getId() {
        return IDGenerator.generate("usr");
    }
}