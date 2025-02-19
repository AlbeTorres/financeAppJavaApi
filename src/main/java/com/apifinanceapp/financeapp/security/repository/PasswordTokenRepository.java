package com.apifinanceapp.financeapp.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.security.model.PasswordToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, String> {

    public Optional<PasswordToken> findByEmail(String email);

    public Optional<PasswordToken> findByToken(String token);

}