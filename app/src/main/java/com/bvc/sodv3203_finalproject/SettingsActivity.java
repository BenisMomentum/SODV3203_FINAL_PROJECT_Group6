package com.bvc.sodv3203_finalproject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {


    public SharedPreferences sharedPreferences; //for dark mode switch btn

    public ImageButton homeBarBtn_workout;

    public Switch darkModeSwitch; //darkmode swicth nbtn

    ImageButton homeBtn, workoutBtn, searchBtn, settingsBtn, backBtn;

    private final static String PREF_SETTINGS_KEY = "settings";
    private final static String PREF_DARKMODE_KEY = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        //------------------------------------------------footer btns
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);
        backBtn = findViewById(R.id.btn_goBack);


        homeBtn.setOnClickListener(view -> navigateTo(MainActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(MainActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(view -> navigateTo(SettingsActivity.class));
        backBtn.setOnClickListener(view -> finish());

        //--

        //darkmode switch
        darkModeSwitch = findViewById(R.id.settings_darkModeSwitch);


        //used for dark_mode
        sharedPreferences = getSharedPreferences(PREF_SETTINGS_KEY, MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean(PREF_DARKMODE_KEY, false);

        setDefaultNightMode(isDarkModeOn);

        darkModeSwitch.setChecked(isDarkModeOn);

        //not too sure about this
        ImageButton btnForgotPassword = findViewById(R.id.settings_btn_forgotPwd);
        ImageButton btnDeleteAccount = findViewById(R.id.settings_btn_DelAcc);

        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        });

    }

    public void settings_applyChanges(View view){

        boolean isDark = darkModeSwitch.isChecked();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("dark_mode", isDark);

        editor.apply();

        setDefaultNightMode(isDark);

        finish();
    }

    //Function made to keep code DRY
    private void setDefaultNightMode(boolean isModeNight){
        if(isModeNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    //to have the footer image buttons functional
    private void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(SettingsActivity.this, activityClass);
        startActivity(intent);
    }
}
