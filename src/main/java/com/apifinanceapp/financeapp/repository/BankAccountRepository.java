package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
