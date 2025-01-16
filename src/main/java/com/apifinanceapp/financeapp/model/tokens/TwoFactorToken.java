package com.apifinanceapp.financeapp.model.tokens;

import java.time.LocalDateTime;

public class TwoFactorToken extends Token {

    public TwoFactorToken() {
        super();
    }

    public TwoFactorToken(String id, String email, String token, LocalDateTime expires) {
        super(id, email, token, expires);

    }

}
