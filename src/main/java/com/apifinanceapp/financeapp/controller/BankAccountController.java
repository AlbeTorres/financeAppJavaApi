package com.apifinanceapp.financeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.BankAccount;
import com.apifinanceapp.financeapp.service.BankAccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> getBankAccountsByUser() {
        return bankAccountService.getBankAccountsByUser();
    }

    @GetMapping("/{id}")
    public BankAccount geBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount);
    }

    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        return bankAccountService.updateBankAccount(id, bankAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable String id) {
        bankAccountService.deleteBankAccount(id);
    }

}
