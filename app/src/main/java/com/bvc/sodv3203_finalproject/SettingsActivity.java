package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.INavigation;

public class SettingsActivity extends AppCompatActivity implements IGoBack, INavigation {

    public SharedPreferences sharedPreferences; // for dark mode switch button
    public Switch darkModeSwitch; // dark mode switch button
    public ImageButton homeBtn, workoutBtn, searchBtn, settingsBtn, backBtn;

    public final static String PREF_SETTINGS_KEY = "settings";
    public final static String PREF_DARKMODE_KEY = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        //------------------------------------------------footer buttons
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);
        backBtn = findViewById(R.id.btn_goBack);

        // Disable settings button since we're already on the SettingsActivity
        settingsBtn.setEnabled(false);
        homeBtn.setOnClickListener(view -> navigateTo(HomePageActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(WorkoutLogActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));

        backBtn.setOnClickListener(this::btn_GoBack);

        // Dark mode switch setup
        darkModeSwitch = findViewById(R.id.settings_darkModeSwitch);

        // Used for dark_mode
        sharedPreferences = getSharedPreferences(PREF_SETTINGS_KEY, MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean(PREF_DARKMODE_KEY, false);

        darkModeSwitch.setChecked(isDarkModeOn);

        // Update the theme only when the switch is toggled
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Just update the shared preferences for the dark mode state
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREF_DARKMODE_KEY, isChecked);
            editor.apply();
        });

    }


    //this code below fixes the darkmode glicthing issue
    public void settings_applyChanges(View view) {
        boolean isDark = darkModeSwitch.isChecked();

        // Apply the theme change when the Apply Changes button is clicked
        setDefaultNightMode(isDark);

        // Save the user's choice to preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_DARKMODE_KEY, isDark);
        editor.apply();

        // Optional: Finish the activity or show a confirmation toast
        finish(); // Optionally finish the activity, or you can show a confirmation message
    }

    // Function to set default night mode based on preference
    public static void setDefaultNightMode(boolean isModeNight) {
        if (isModeNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Footer buttons navigation functionality
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(SettingsActivity.this, activityClass);
        startActivity(intent);
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish(); // Go back to the previous activity
    }
}
