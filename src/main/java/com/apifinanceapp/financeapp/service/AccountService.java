package com.apifinanceapp.financeapp.service;

import java.util.ArrayList;
import java.util.List;

import com.apifinanceapp.financeapp.model.Account;

public class AccountService {
    Account account = Account.builder().id("1").type("1").provider("google").providerAccountId("1").build();
    List<Account> lista = new ArrayList<Account>();

    public List<Account> getAccountsByUser() {
        lista.add(account);
        return lista;
    }

    public Account getAccountById(String id) {
        System.out.println(id);
        return lista.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    public Account createAccount(Account account) {
        System.out.println(account);
        return account;
    }

    public Account updateAccount(String id, Account account) {
        System.out.println("ID: " + id + ", User: " + account);

        // Usar un solo stream para buscar y actualizar
        for (Account accountx : lista) {
            if (account.getId().equals(id)) {
                updateFields(accountx, account);
                return account; // Devolver el account actualizado inmediatamente
            }
        }

        return null; // Devolver null si no se encontró el account
    }

    public String deleteAccount(String id) {

        System.out.println(id);

        for (Account account : lista) {
            if (account.getId().equals(id)) {
                lista.remove(account);
                return "Account satisfactory deleted"; // Salir del bucle si se eliminó
            }
        }

        return "Account not found"; // Devolver mensaje de eliminado
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
