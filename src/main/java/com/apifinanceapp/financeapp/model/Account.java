package com.apifinanceapp.financeapp.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private String provider;
    private String providerAccountId;
    private String refreshToken;
    private String accessToken;
    private Integer expiresAt;
    private String tokenType;
    private String scope;
    private String idToken;
    private String sessionState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
