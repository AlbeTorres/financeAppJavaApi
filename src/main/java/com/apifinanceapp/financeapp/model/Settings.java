package com.apifinanceapp.financeapp.model;

import com.apifinanceapp.financeapp.model.common.Languaje;

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
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Languaje language;
    @OneToOne(mappedBy = "settings", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

}
