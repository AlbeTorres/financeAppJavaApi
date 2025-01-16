package com.apifinanceapp.financeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.BankAccount;
import com.apifinanceapp.financeapp.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/bankaccount")

public class BankAccountController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping
    public List<BankAccount> getBankAccountsByUser() {
        return bankAccountRepository.findAll();
    }

}
