package com.apifinanceapp.financeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinanceapp.financeapp.model.Settings;

import com.apifinanceapp.financeapp.repository.SettingsRepository;

@Service
public class SettingsService {

    @Autowired
    SettingsRepository settingsRepository;

    public List<Settings> getSettingsByUser() {
        return settingsRepository.findAll();
    }

    public Settings getSettingsById(String id) {
        return settingsRepository.findById(id).orElse(null);
    }

    public Settings updateSettings(String id, Settings settings) {

        Settings target = settingsRepository.findById(id).orElse(null);

        if (target != null) {
            updateFields(target, settings);
            return settingsRepository.save(target);
        }
        return null;
    }

    public Settings createSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    public String deleteSettings(String id) {

        Settings target = settingsRepository.findById(id).orElse(null);

        if (target != null) {
            settingsRepository.deleteById(id);
            return "Settings satisfactory deleted"; // Salir del bucle si se eliminó el usuario

        }

        return "Settings not found"; // Devolver mensaje de usuario eliminado
    }

    private void updateFields(Settings target, Settings source) {
        // Actualizar solo los campos no nulos
        if (source.getLanguage() != null)
            target.setLanguage(source.getLanguage());
        target.setDarkMode(source.isDarkMode());
        target.setNotifications(source.isNotifications()); // Campo booleano siempre se actualiza
    }
}
