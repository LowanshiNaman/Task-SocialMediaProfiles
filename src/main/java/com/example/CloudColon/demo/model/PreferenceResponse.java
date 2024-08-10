package com.example.CloudColon.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreferenceResponse {

    private String status;
    private String message;
    private Preference preference;

    public PreferenceResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
