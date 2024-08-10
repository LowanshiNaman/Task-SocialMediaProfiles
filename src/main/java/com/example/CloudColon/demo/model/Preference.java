package com.example.CloudColon.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    private String theme;
    private List<String> socialMedia;

    public Preference() {
        this.theme = "light";
        this.socialMedia = new ArrayList<>();
    }

    public Preference(String theme, List<String> socialMedia) {
        this.theme = theme;
        this.socialMedia = socialMedia;
    }

    // Getters and Setters
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<String> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<String> socialMedia) {
        this.socialMedia = socialMedia;
    }
}
