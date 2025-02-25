package com.apifinanceapp.financeapp.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.security.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

    public Optional<VerificationToken> findByEmail(String email);

    public Optional<VerificationToken> findByToken(String token);

}
