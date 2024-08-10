package com.example.CloudColon.demo.exception;

import com.example.CloudColon.demo.service.PreferenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreferenceService preferenceService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetPreferencesWhenUserNotFound() throws Exception {
        String username = "nonexistentuser";
        when(preferenceService.getPreferences(username))
                .thenThrow(new UserNotFoundException(""));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/preferences/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}
