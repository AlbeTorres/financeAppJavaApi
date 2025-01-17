package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.BankAccount;

@Service
public class BankAccountService {

    public List<BankAccount> getBankAccountsByUser() {
        return null;
    }

    public BankAccount getBankAccountById(String id) {
        System.out.println(id);
        return null;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        System.out.println(bankAccount);
        return null;
    }

    public BankAccount updateBankAccount(String id, BankAccount bankAccount) {
        System.out.println("" + id + bankAccount);
        return null;
    }

    public void deleteBankAccount(String id) {
        System.out.println(id);
    }

}
