package com._naptic.trakr_api.notifications;

import com._naptic.trakr_api.notifications.services.MailService;
import com._naptic.trakr_api.tokens.events.ResetPasswordTokenCreated;
import com._naptic.trakr_api.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final MailService mailService;

    @Async
    @EventListener
    public void handleResetPasswordTokenCreated(ResetPasswordTokenCreated event) {
        User user = event.user();
        String token = event.token();
        String baseUrl = "http://localhost:3000/reset-password";
        String url = baseUrl + "?token=" + token;

        String content = """
                Hello, %s
                Click the following link to reset your password: %s.
                """.formatted(user.getFullName(), url);

        mailService.send(user.getEmail(), "RESET PASSWORD LINK", content);
    }

}
