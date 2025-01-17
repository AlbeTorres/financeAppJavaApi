package com.apifinanceapp.financeapp.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private BigInteger amount;

    private String payee;

    private String notes;

    private LocalDateTime date;

    private String categoryId;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;
}