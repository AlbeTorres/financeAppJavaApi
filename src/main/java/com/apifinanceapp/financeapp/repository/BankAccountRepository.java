package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
