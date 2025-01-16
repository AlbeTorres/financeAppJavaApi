package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinanceapp.financeapp.model.Settings;

public interface SettingsRepository extends JpaRepository<Settings, String> {

}
