package com.example.CloudColon.demo.controller;

import com.example.CloudColon.demo.exception.ErrorBody;
import com.example.CloudColon.demo.exception.ThemeNotFoundException;
import com.example.CloudColon.demo.exception.UserNotFoundException;
import com.example.CloudColon.demo.model.Preference;
import com.example.CloudColon.demo.service.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.CloudColon.demo.constant.PreferenceConstant.V_1_API;

@RestController
@RequestMapping(V_1_API + "/preferences")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    @Operation(summary = "Get user preferences", description = "Retrieve the preferences for a specific user. Returns default preferences if none exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferences retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class)))
    })
    @GetMapping("/{username}")
    public ResponseEntity<Preference> getPreferences(@PathVariable String username) {
        Preference preference = preferenceService.getPreferences(username);
        if (preference == null) {
            throw new UserNotFoundException("Preferences not found for user: " + username);
        }
        return ResponseEntity.ok(preference);
    }

    @Operation(summary = "Update user preferences", description = "Update the preferences for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferences updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @ApiResponse(responseCode = "400", description = "Invalid theme",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class)))
    })
    @PostMapping("/update/{username}")
    public ResponseEntity<Preference> updatePreferences(
            @PathVariable String username,
            @RequestBody Preference preference) {
        if (!(preference.getTheme().equalsIgnoreCase("light") || preference.getTheme().equalsIgnoreCase("dark"))) {
            throw new ThemeNotFoundException("Theme not Valid: " + preference.getTheme());
        }
        Preference updatedPreference = preferenceService.updatePreferences(username, preference);
        if (updatedPreference == null) {
            throw new UserNotFoundException("Preferences not found for user: " + username);
        }
        return ResponseEntity.ok(updatedPreference);
    }

    @Operation(summary = "Add or update user preferences", description = "Add or update preferences for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preferences added or updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @ApiResponse(responseCode = "400", description = "Invalid theme",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class)))
    })
    @PostMapping("/add/{username}")
    public ResponseEntity<Preference> addOrUpdatePreferences(
            @PathVariable String username,
            @RequestBody Preference preference) {
        if (!(preference.getTheme().equalsIgnoreCase("light") || preference.getTheme().equalsIgnoreCase("dark"))) {
            throw new ThemeNotFoundException("Theme not Valid: " + preference.getTheme());
        }
        Preference updatedPreference = preferenceService.addOrUpdatePreferences(username, preference);
        if (updatedPreference == null) {
            throw new UserNotFoundException("Preferences not found for user: " + username);
        }
        return ResponseEntity.ok(updatedPreference);
    }

    @Operation(summary = "Delete user preferences", description = "Delete the preferences for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Preferences deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorBody.class)))
    })
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deletePreferences(@PathVariable String username) {
        preferenceService.deletePreferences(username);
        return ResponseEntity.noContent().build();
    }
}
