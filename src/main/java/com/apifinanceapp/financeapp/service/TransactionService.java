package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apifinanceapp.financeapp.model.Transaction;
import com.apifinanceapp.financeapp.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByUser() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(String id, Transaction transaction) {

        Transaction target = transactionRepository.findById(id).orElse(null);

        if (transaction != null) {
            updateFields(target, transaction);
            return transactionRepository.save(target);
        }
        return null;
    }

    public String deleteTransaction(String id) {

        Transaction target = transactionRepository.findById(id).orElse(null);

        if (target != null) {
            transactionRepository.deleteById(id);
            return "Transaction satisfactory deleted"; // Salir del bucle si se eliminó el usuario

        }

        return "Transaction not found"; // Devolver mensaje de usuario eliminado
    }

    private void updateFields(Transaction target, Transaction source) {
        // Actualizar solo los campos no nulos
        if (source.getAmount() != null)
            target.setAmount(source.getAmount());
        if (source.getPayee() != null)
            target.setPayee(source.getPayee());
        if (source.getNotes() != null)
            target.setNotes(source.getNotes());
        if (source.getCategory() != null)
            target.setCategory(source.getCategory());
        if (source.getDate() != null)
            target.setDate(source.getDate());
    }

}
