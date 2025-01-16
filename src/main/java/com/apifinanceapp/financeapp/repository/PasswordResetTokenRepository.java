package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.tokens.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {

}
