package com.apifinanceapp.financeapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TwoFactorConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;

    public TwoFactorConfirmation() {
    }

    public TwoFactorConfirmation(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TwoFactorConfirmation [id=" + id + ", userId=" + userId + "]";
    }

}
