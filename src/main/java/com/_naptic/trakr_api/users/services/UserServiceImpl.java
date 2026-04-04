package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.shared.exceptions.common.ConflictException;
import com._naptic.trakr_api.shared.exceptions.common.NotFoundException;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.UserMapper;
import com._naptic.trakr_api.users.UserRepository;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(CreateUserDto dto) {
        this.ensureEmailNotInUse(dto.getEmail());

        User entity = mapper.toEntity(dto);

        if (dto.getPassword() != null) {
            String encodedPwd = passwordEncoder.encode(dto.getPassword());

            dto.setPassword(encodedPwd);
        }

        User savedUser = repository.save(entity);

        return mapper.toResponse(savedUser);
    }

    @Override
    public UserResponse update(String userId, UpdateUserDto dto) {
        User existingUser = getExistingUser(userId);

        if (dto.getEmail() != null) {
            this.ensureEmailNotInUse(dto.getEmail());
        }

        if (dto.getPassword() != null) {
            String encodedPwd = passwordEncoder.encode(dto.getPassword());

            dto.setPassword(encodedPwd);
        }

        mapper.updateEntity(dto, existingUser);

        User updatedUser = repository.save(existingUser);

        return mapper.toResponse(updatedUser);
    }

    @Override
    public User getExistingUser(String userId) {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User getExistingUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void ensureEmailNotInUse(String email) {
        Optional<User> existingUser = repository.findByEmail(email);

        if (existingUser.isPresent()) throw new ConflictException("Email address already in use");
    }

}
