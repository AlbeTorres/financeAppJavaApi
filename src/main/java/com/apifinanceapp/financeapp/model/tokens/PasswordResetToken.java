package com.apifinanceapp.financeapp.model.tokens;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class PasswordResetToken extends Token {
    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(String id, String email, String token, LocalDateTime expires) {
        super(id, email, token, expires);
    }

}
