package com.apifinanceapp.financeapp.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.security.model.TwoFactorCode;

public interface TwoFactorCodeRepository extends JpaRepository<TwoFactorCode, String> {

    public Optional<TwoFactorCode> findByEmail(String email);

    public Optional<TwoFactorCode> findByCode(String code);

}
