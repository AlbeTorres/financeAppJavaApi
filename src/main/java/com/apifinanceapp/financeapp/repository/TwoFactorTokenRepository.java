package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.tokens.TwoFactorToken;

public interface TwoFactorTokenRepository extends JpaRepository<TwoFactorToken, String> {

}
