package com.apifinanceapp.financeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.BankAccount;

@Service
public class BankAccountService {
    BankAccount bankAccount = new BankAccount("1", "1", 1000, "Bank of America", null, null);
    List<BankAccount> lista = new ArrayList<BankAccount>();

    public List<BankAccount> getBankAccountsByUser() {
        lista.add(bankAccount);
        return lista;
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
