package com.apifinanceapp.financeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.Transaction;
import com.apifinanceapp.financeapp.service.TransactionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getTransactionByUser() {
        return transactionService.getTransactionsByUser();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransaction(id);
    }

}
