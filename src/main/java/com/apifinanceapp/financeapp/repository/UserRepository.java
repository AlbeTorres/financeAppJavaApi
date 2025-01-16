package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
