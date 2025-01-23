package com.apifinanceapp.financeapp.service;

import java.util.ArrayList;
import java.util.List;

import com.apifinanceapp.financeapp.model.Settings;
import com.apifinanceapp.financeapp.model.common.Languaje;

public class SettingsService {

    Settings settings = new Settings("1", Languaje.ES, true, true, null);

    List<Settings> settingslist = new ArrayList<Settings>();

    public void populateSettings() {
        settingslist.add(settings);
    }

    public List<Settings> getSettingsByUser() {
        return settingslist;
    }

    public Settings getSettingsById(String id) {
        return settingslist.stream().filter(settings -> settings.getId().equals(id)).findFirst().orElse(null);
    }

    public Settings updateSettings(String id, Settings settings) {

        for (Settings s : settingslist) {
            if (s.getId().equals(id)) {
                updateFields(settings, s);
                return s;
            }
        }

        return null;
    }

    public Settings createSettings(Settings settings) {
        settingslist.add(settings);
        return settings;
    }

    public String deleteSettings(String id) {
        for (Settings s : settingslist) {
            if (s.getId().equals(id)) {
                updateFields(settings, s);
                return "Settings with id " + id + " has been deleted";
            }
        }
        return "Settings not found";
    }

    private void updateFields(Settings target, Settings source) {
        // Actualizar solo los campos no nulos
        if (source.getLanguage() != null)
            target.setLanguage(source.getLanguage());
        target.setDarkMode(source.isDarkMode());
        target.setNotifications(source.isNotifications()); // Campo booleano siempre se actualiza
    }
}
