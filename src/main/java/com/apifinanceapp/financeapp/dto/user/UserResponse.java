package com.apifinanceapp.financeapp.dto.user;

import java.time.LocalDateTime;
import java.util.List;

import com.apifinanceapp.financeapp.model.common.Role;
import com.apifinanceapp.financeapp.model.Account;
import com.apifinanceapp.financeapp.model.BankAccount;
import com.apifinanceapp.financeapp.model.Category;
import com.apifinanceapp.financeapp.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponse {
        private String id;
        private String name;
        private String username;
        private String email;
        private LocalDateTime emailVerified;
        private Role role;
        private String image;
        private boolean isTwofactorEnabled;
        private List<Account> accounts;
        private List<BankAccount> bankAccounts;
        private List<Category> categories;
        private List<Transaction> transactions;

        public UserResponse(String name, String username, String email, Role role, String image) {
                this.name = name;
                this.username = username;
                this.email = email;
                this.role = role;
                this.image = image;
        }

}