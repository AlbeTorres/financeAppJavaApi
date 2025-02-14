package com.apifinanceapp.financeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword,'%')) OR u.email LIKE LOWER(CONCAT('%', :keyword,'%'))")
    public Optional<List<User>> searchUsers(String keyword);

    @Query("SELECT u FROM User u WHERE u.email = :credential OR u.username = :credential")
    Optional<User> findByAnyCredential(@Param("credential") String credential);

}
