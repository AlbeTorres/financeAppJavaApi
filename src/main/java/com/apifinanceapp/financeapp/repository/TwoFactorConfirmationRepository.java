package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.TwoFactorConfirmation;

public interface TwoFactorConfirmationRepository extends JpaRepository<TwoFactorConfirmation, String> {

}
