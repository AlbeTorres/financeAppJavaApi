package com.apifinanceapp.financeapp.model;

import java.util.List;

public class BankAccount {
    private String id;
    private String plaidId;
    private String userId;
    private long balance;
    private String name;
    private List<Transaction> transactions;

    public BankAccount() {
    }

    public BankAccount(String id, String plaidId, String userId, long balance, String name,
            List<Transaction> transactions) {
        this.id = id;
        this.plaidId = plaidId;
        this.userId = userId;
        this.balance = balance;
        this.name = name;
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaidId() {
        return plaidId;
    }

    public void setPlaidId(String plaidId) {
        this.plaidId = plaidId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAccount [id=" + id + ", plaidId=" + plaidId + ", userId=" + userId + ", balance=" + balance
                + ", name=" + name + ", transactions=" + transactions + "]";
    }

}
