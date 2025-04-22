package com.bvc.sodv3203_finalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.Utility;


/**
 * Settings activity
 *
 * Allows the user to switch between Light and Dark Mode easily.
 */
public class SettingsActivity extends AppCompatActivity implements IGoBack {

    //Used for any needed settings.
    public SharedPreferences sharedPreferences;
    public SwitchCompat darkModeSwitch; // dark mode switch button
    public ImageButton homeBtn, workoutBtn, searchBtn, settingsBtn, backBtn;

    //Static keys for centralization.
    public final static String PREF_SETTINGS_KEY = "settings";
    public final static String PREF_DARKMODE_KEY = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        //Home bar buttons
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);

        backBtn = findViewById(R.id.btn_goBack);
        backBtn.setOnClickListener(this::btn_GoBack);

        // Dark mode switch setup
        darkModeSwitch = findViewById(R.id.settings_darkModeSwitch);

        // Update the theme only when the switch is toggled
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Just update the shared preferences for the dark mode state
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREF_DARKMODE_KEY, isChecked);
            editor.apply();
        });

    }

    @Override
    public void onStart(){
        super.onStart();

        // Used for dark_mode
        sharedPreferences = getSharedPreferences(PREF_SETTINGS_KEY, MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean(PREF_DARKMODE_KEY, Utility.GetSystemIsDark(this));
        darkModeSwitch.setChecked(isDarkModeOn);

        Utility.SetupHomeBarButtons(homeBtn, workoutBtn, searchBtn, settingsBtn, this);
    }


    /**
     *  Button hook that allows us to properly apply any settings changes
     *  the user has made.
     * @param view Necessary for button hook.
     */
    public void settings_applyChanges(View view) {
        boolean isDark = darkModeSwitch.isChecked();

        // Apply the theme change when the Apply Changes button is clicked
        setDefaultNightMode(isDark);

        // Save the user's choice to preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_DARKMODE_KEY, isDark);
        editor.apply();
    }

    /**
     * Function to set default night mode based on preference
     */
    public static void setDefaultNightMode(boolean isModeNight) {
        if (isModeNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish(); // Go back to the previous activity
    }
}
