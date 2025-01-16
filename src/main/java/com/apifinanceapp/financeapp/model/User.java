package com.apifinanceapp.financeapp.model;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private String emailVerified; // Cambiar a LocalDateTime si usas una librería como java.time
    private String password;
    private String role;
    private String image;
    private List<Account> account;
    private boolean isTwofactorEnabled;
    private TwoFactorConfirmation twoFactorConfirmation;
    private Settings settings;

    public User() {
    }

    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public boolean isTwofactorEnabled() {
        return isTwofactorEnabled;
    }

    public void setTwofactorEnabled(boolean isTwofactorEnabled) {
        this.isTwofactorEnabled = isTwofactorEnabled;
    }

    public TwoFactorConfirmation getTwoFactorConfirmation() {
        return twoFactorConfirmation;
    }

    public void setTwoFactorConfirmation(TwoFactorConfirmation twoFactorConfirmation) {
        this.twoFactorConfirmation = twoFactorConfirmation;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", emailVerified=" + emailVerified
                + ", password=" + password + ", role=" + role + ", image=" + image + ", account=" + account
                + ", isTwofactorEnabled=" + isTwofactorEnabled + ", twoFactorConfirmation=" + twoFactorConfirmation
                + ", settings=" + settings + "]";
    }

}
