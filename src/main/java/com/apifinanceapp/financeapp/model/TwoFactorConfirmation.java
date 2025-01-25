package com.apifinanceapp.financeapp.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class TwoFactorConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne(mappedBy = "twoFactorConfirmation", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

}
