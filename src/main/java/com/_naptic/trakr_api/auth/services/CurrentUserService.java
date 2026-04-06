package com._naptic.trakr_api.auth.services;

import com._naptic.trakr_api.auth.models.CustomPrincipal;
import com._naptic.trakr_api.users.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
    private CustomPrincipal getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal != null) {
            return (CustomPrincipal) principal;
        }

        return null;
    }

    public User getUser() {
        CustomPrincipal principal = getPrincipal();
        return principal == null ? null : principal.getUser();
    }

    public String getUserId() {
        CustomPrincipal principal = getPrincipal();

        return principal == null ? null : principal.getUser().getId();
    }
}
