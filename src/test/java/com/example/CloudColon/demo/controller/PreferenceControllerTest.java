package com.example.CloudColon.demo.controller;

import com.example.CloudColon.demo.model.Preference;
import com.example.CloudColon.demo.service.PreferenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(PreferenceController.class)
public class PreferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreferenceService preferenceService;

    @InjectMocks
    private PreferenceController preferenceController;

    @Test
    void testGetPreferences() throws Exception {
        PreferenceController preferenceController1 = Mockito.mock(PreferenceController.class);
        Preference preference = new Preference("dark", List.of("Facebook", "Twitter"));
        ResponseEntity<Preference> k = new ResponseEntity<>(preference, HttpStatusCode.valueOf(HttpStatus.OK.value()));

        when(preferenceService.getPreferences("testuser")).thenReturn(preference);
        ResponseEntity<Preference> pre = preferenceController1.getPreferences("User");
        Assertions.assertEquals(preference.getSocialMedia(), List.of("Facebook", "Twitter"));
    }

    @Test
    void testUpdatePreferences() throws Exception {
        PreferenceController preferenceController1 = Mockito.mock(PreferenceController.class);

        Preference preference = new Preference("dark", List.of("Facebook", "Twitter"));
        ResponseEntity<Preference> k = new ResponseEntity<>(preference, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        when(preferenceController1.updatePreferences("testuser", Preference.class.newInstance())).thenReturn(k);
        Assertions.assertEquals(preference.getSocialMedia(), List.of("Facebook", "Twitter"));
    }

    @Test
    void testAddOrUpdatePreferences() throws Exception {
        PreferenceController preferenceController1 = Mockito.mock(PreferenceController.class);
        Preference preference = new Preference("light", List.of("Instagram", "LinkedIn"));
        ResponseEntity<Preference> k = new ResponseEntity<>(preference, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        when(preferenceService.addOrUpdatePreferences("testuser", Preference.class.newInstance())).thenReturn(preference);
        preferenceController1.addOrUpdatePreferences("user", Preference.class.newInstance());
        Assertions.assertNotEquals(preference.getSocialMedia(), List.of("Facebook", "Twitter"));
    }

    @Test
    void negativeTestDeletePreferencesWithWrongEndPoint() throws Exception {
        PreferenceController preferenceController1 = Mockito.mock(PreferenceController.class);
        Preference preference = new Preference("light", List.of("Instagram", "LinkedIn"));
        doNothing().when(preferenceService).deletePreferences("testuser");
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/testuser"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        preferenceController1.deletePreferences("user");
        ResponseEntity<Preference> k = new ResponseEntity<>(preference, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
        Assertions.assertEquals(404, k.getStatusCode().value());
    }

}
