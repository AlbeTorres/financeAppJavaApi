package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.tokens.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

}
