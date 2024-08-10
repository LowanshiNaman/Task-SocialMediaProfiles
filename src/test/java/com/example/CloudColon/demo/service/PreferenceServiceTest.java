package com.example.CloudColon.demo.service;

import com.example.CloudColon.demo.model.Preference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreferenceServiceTest {

    private PreferenceService preferenceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        preferenceService = new PreferenceService();
    }

    @Test
    void testGetPreferencesWhenPresent() {
        Preference expectedPreference = new Preference("dark", List.of("Facebook", "Twitter"));
        preferenceService.updatePreferences("testUser", expectedPreference);
        Preference actualPreference = preferenceService.getPreferences("testUser");
        assertEquals(expectedPreference, actualPreference);
    }

    @Test
    void testGetPreferencesWhenNotPresent() {
        Preference actualPreference = preferenceService.getPreferences("unknownUser");
        assertNotNull(actualPreference);
        assertEquals("default", actualPreference.getTheme());
        assertTrue(actualPreference.getSocialMedia().isEmpty());
    }

    @Test
    void testUpdatePreferences() {
        Preference newPreference = new Preference("light", List.of("Instagram", "LinkedIn"));

        Preference updatedPreference = preferenceService.updatePreferences("testUser", newPreference);
        assertEquals(newPreference, updatedPreference);
        assertEquals(newPreference, preferenceService.getPreferences("testUser"));
    }

    @Test
    void testAddOrUpdatePreferences() {
        Preference newPreference = new Preference("light", List.of("Instagram", "LinkedIn"));
        Preference updatedPreference = preferenceService.addOrUpdatePreferences("testUser", newPreference);
        assertEquals(newPreference, updatedPreference);
        assertEquals(newPreference, preferenceService.getPreferences("testUser"));
    }


    @Test
    void testDeletePreferencesWhenNotPresent() {
        preferenceService.deletePreferences("unknownUser");
        assertNotNull(preferenceService.getPreferences("unknownUser"));
    }
}
