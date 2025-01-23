package com.apifinanceapp.financeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinanceapp.financeapp.model.Settings;
import com.apifinanceapp.financeapp.service.SettingsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    SettingsService settingsService = new SettingsService();

    @GetMapping
    public List<Settings> getSettingsByUser() {
        return settingsService.getSettingsByUser();
    }

    @GetMapping("/{id}")
    public Settings getSettingsById(@PathVariable String id) {
        return settingsService.getSettingsById(id);
    }

    @PostMapping
    public Settings createSettings(@RequestBody Settings settings) {
        return settingsService.createSettings(settings);
    }

    @PutMapping("/{id}")
    public Settings updateSettings(@PathVariable String id, @RequestBody Settings settings) {
        return settingsService.updateSettings(id, settings);
    }

    @DeleteMapping("/{id}")
    public String deleteSettings(@PathVariable String id) {
        return settingsService.deleteSettings(id);
    }

}
