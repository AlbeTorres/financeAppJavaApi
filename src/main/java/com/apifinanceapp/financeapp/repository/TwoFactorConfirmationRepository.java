package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.TwoFactorConfirmation;

@Repository
public interface TwoFactorConfirmationRepository extends JpaRepository<TwoFactorConfirmation, String> {

}
