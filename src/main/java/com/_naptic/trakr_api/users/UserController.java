package com._naptic.trakr_api.users;

import com._naptic.trakr_api.shared.responses.CustomApiResponse;
import com._naptic.trakr_api.shared.responses.WrappedResponse;
import com._naptic.trakr_api.users.dtos.UpdateUserDto;
import com._naptic.trakr_api.users.dtos.UserResponse;
import com._naptic.trakr_api.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Users")
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user")
    public ResponseEntity<CustomApiResponse<UserResponse>> update(@PathVariable String userId, @Valid @RequestBody UpdateUserDto dto) {
        UserResponse response = service.update(userId, dto);

        return WrappedResponse.of(response, "User updated successfully");
    }
}
