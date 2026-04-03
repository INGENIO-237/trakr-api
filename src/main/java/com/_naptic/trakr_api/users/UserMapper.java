package com._naptic.trakr_api.users;

import com._naptic.trakr_api.users.dtos.CreateUserDto;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toEntity(CreateUserDto dto);

    UserResponse toResponse(User entity);

    void updateEntity(UpdateUserDto dto, @MappingTarget User entity);
}
