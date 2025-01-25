package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.Account;
import com.apifinanceapp.financeapp.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccountsByUser() {
        return accountRepository.findAll();
    }

    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(String id, Account account) {
        System.out.println("ID: " + id + ", User: " + account);

        Account target = accountRepository.findById(id).orElse(null);

        if (target != null) {
            updateFields(target, account);
            return accountRepository.save(target);
        }
        return null;
    }

    public String deleteAccount(String id) {

        Account target = accountRepository.findById(id).orElse(null);

        if (target != null) {
            accountRepository.deleteById(id);
            return "Account satisfactory deleted"; // Salir del bucle si se eliminó el usuario

        }

        return "Account not found"; // Devolver mensaje de usuario eliminado
    }

    private void updateFields(Account target, Account source) {
        // Actualizar solo los campos no nulos
        if (source.getType() != null)
            target.setType(source.getType());
        if (source.getProvider() != null)
            target.setProvider(source.getProvider());
        if (source.getProviderAccountId() != null)
            target.setProviderAccountId(source.getProviderAccountId());
        if (source.getRefreshToken() != null)
            target.setRefreshToken(source.getRefreshToken());
        if (source.getAccessToken() != null)
            target.setAccessToken(source.getAccessToken());
        if (source.getExpiresAt() != null)
            target.setExpiresAt(source.getExpiresAt());
        if (source.getTokenType() != null)
            target.setTokenType(source.getTokenType());
        if (source.getScope() != null)
            target.setScope(source.getScope());
        if (source.getIdToken() != null)
            target.setIdToken(source.getIdToken());
        if (source.getSessionState() != null)
            target.setSessionState(source.getSessionState());

        // Campos de tipo LocalDateTime
        if (source.getCreatedAt() != null)
            target.setCreatedAt(source.getCreatedAt());
        if (source.getUpdatedAt() != null)
            target.setUpdatedAt(source.getUpdatedAt());
    }
}
