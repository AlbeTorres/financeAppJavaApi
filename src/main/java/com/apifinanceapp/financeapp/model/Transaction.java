package com.apifinanceapp.financeapp.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Transaction {

    private String id;

    private BigInteger amount;

    private String payee;

    private String notes;

    private LocalDateTime date;

    private String accountId;

    private String categoryId;

    private String userId;

    // Constructor requerido por JPA
    public Transaction() {
    }

    public Transaction(String id, BigInteger amount, LocalDateTime date, String accountId, String categoryId,
            String userId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", payee=" + payee + ", notes=" + notes + ", date="
                + date + ", accountId=" + accountId + ", categoryId=" + categoryId + ", userId=" + userId + "]";
    }

}
