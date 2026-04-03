package com._naptic.trakr_api.users.services;

import com._naptic.trakr_api.shared.exceptions.common.ConflictException;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.UserMapper;
import com._naptic.trakr_api.users.UserRepository;
import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public UserResponse create(CreateUserDto dto) {
        Optional<User> existingUser = repository.findByEmail(dto.email());

        if (existingUser.isPresent()) throw new ConflictException("Email address already in use");

        User entity = mapper.toEntity(dto);

        User savedUser = repository.save(entity);

        return mapper.toResponse(savedUser);
    }


}
