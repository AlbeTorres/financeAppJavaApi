package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
