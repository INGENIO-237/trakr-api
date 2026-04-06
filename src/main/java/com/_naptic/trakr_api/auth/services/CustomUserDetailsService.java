package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.auth.models.CustomPrincipal;
import com._naptic.trakr_api.shared.exceptions.common.NotFoundException;
import com._naptic.trakr_api.users.User;
import com._naptic.trakr_api.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);

        if(user.isEmpty()) throw new NotFoundException("The user you are looking for does not exist");

        return new CustomPrincipal(user.get());
    }
}
