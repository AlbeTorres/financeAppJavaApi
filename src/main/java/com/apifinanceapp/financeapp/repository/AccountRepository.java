package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
