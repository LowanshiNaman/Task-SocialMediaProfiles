package com.example.CloudColon.demo.service;

import com.example.CloudColon.demo.model.Preference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreferenceService {

    private final Map<String, Preference> preferencesDb = new HashMap<>();

    // Get user preferences
    public Preference getPreferences(String username) {
        return preferencesDb.getOrDefault(username, new Preference("default", List.of()));
    }

    // Update user preferences
    public Preference updatePreferences(String username, Preference preference) {
        preferencesDb.put(username, preference);
        return preference;
    }

    // Add or update user preferences
    public Preference addOrUpdatePreferences(String username, Preference preference) {
        return updatePreferences(username, preference);
    }

    // Delete user preferences
    public void deletePreferences(String username) {
        preferencesDb.remove(username);
    }
}
