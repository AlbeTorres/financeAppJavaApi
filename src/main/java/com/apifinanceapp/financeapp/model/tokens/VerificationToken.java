package com.apifinanceapp.financeapp.model.tokens;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class VerificationToken extends Token {
    public VerificationToken() {
        super();
    }

    public VerificationToken(String id, String email, String token, LocalDateTime expires) {
        super(id, email, token, expires);
    }
}
