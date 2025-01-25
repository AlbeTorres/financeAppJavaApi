package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.BankAccount;
import com.apifinanceapp.financeapp.repository.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    public List<BankAccount> getBankAccountsByUser() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount updateBankAccount(String id, BankAccount bankAccount) {
        BankAccount target = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            updateFields(target, bankAccount);
            return bankAccountRepository.save(target);
        }
        return null;
    }

    public void deleteBankAccount(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            bankAccountRepository.deleteById(id);
        }
    }

    private void updateFields(BankAccount target, BankAccount source) {
        // Actualizar solo los campos no nulos
        if (source.getName() != null)
            target.setName(source.getName());
        if (source.getPlaidId() != null)
            target.setPlaidId(source.getPlaidId());
        if (source.getBalance() != 0) {
            target.setBalance(source.getBalance());
        }
    }
}
