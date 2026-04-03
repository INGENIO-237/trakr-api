package com._naptic.trakr_api.tokens;

import com._naptic.trakr_api.shared.models.BaseEntity;
import com._naptic.trakr_api.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenHolder extends BaseEntity {
    public static final String ID_PREFIX = "tkn";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @Column(nullable = false)
    private String token = "initial.token";

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Override
    protected String getIdPrefix() {
        return ID_PREFIX;
    }
}
