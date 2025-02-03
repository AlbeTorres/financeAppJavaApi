package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.tokens.TwoFactorToken;

@Repository
public interface TwoFactorTokenRepository extends JpaRepository<TwoFactorToken, String> {

}
