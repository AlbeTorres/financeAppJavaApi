package com.apifinanceapp.financeapp.model.common;

public enum Role {
    ADMIN, USER;

    public String getLowerCase() {
        return this.name().toLowerCase();
    }
}
