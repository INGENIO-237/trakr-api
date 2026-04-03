package com._naptic.trakr_api.tokens.events;

import com._naptic.trakr_api.users.User;

public record ResetPasswordTokenCreated(User user, String token) {
}
