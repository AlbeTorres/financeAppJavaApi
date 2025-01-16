package com.apifinanceapp.financeapp.model;

import com.apifinanceapp.financeapp.model.common.Languaje;

public class Settings {
    private String id;
    private Languaje language;
    private String userId;

    public Settings() {
    }

    public Settings(String id, Languaje language, String userId) {
        this.id = id;
        this.language = language;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Languaje getLanguage() {
        return language;
    }

    public void setLanguage(Languaje language) {
        this.language = language;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Settings [id=" + id + ", language=" + language + ", userId=" + userId + "]";
    }

}
