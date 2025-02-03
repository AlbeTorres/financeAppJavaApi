package com.apifinanceapp.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifinanceapp.financeapp.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, String> {

}
