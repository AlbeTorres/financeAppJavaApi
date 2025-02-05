package com.apifinanceapp.financeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByEmail(String email);

    public User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword,'%')) OR u.email LIKE LOWER(CONCAT('%', :keyword,'%'))")
    public List<User> searchUsers(String keyword);

}
