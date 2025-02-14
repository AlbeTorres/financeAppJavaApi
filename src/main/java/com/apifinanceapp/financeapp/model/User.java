package com.apifinanceapp.financeapp.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.apifinanceapp.financeapp.model.common.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Component
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String username;
    private String email;
    private LocalDateTime emailVerified; // Cambiar a LocalDateTime si usas una librería como java.time
    private String password;
    private Role role;
    private String image;
    private boolean isTwofactorEnabled;

}
