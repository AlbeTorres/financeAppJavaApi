package com.apifinanceapp.financeapp.model.tokens;

import java.time.LocalDateTime;

abstract class Token {

    private String id;
    private String email;
    private String token;
    private LocalDateTime expires;

    public Token() {
    }

    public Token(String id, String email, String token, LocalDateTime expires) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expires = LocalDateTime.now().plusMinutes(15);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", email=" + email + ", token=" + token + ", expires=" + expires + "]";
    }

}
