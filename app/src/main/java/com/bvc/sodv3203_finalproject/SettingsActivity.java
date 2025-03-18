package com.bvc.sodv3203_finalproject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {


    public SharedPreferences sharedPreferences; //for dark mode switch btn

    public Switch darkModeSwitch; //darkmode swicth nbtn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);


        //darkmode switch
        darkModeSwitch = findViewById(R.id.switch_dark_mode);


        //used for dark_mode
        sharedPreferences =getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean("dark_mode", false);

        if(isDarkModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkModeSwitch.setChecked(true);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            darkModeSwitch.setChecked(false);
        }

        //change the theme when switch btn is active
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("dark_mode", true);
            } else {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("dark_mode", false);

            }
            editor.apply();

            //initialize the activity to apply the theme change
            Intent intent = getIntent();
            finish();
            startActivity(intent);

        });


        //not too sure about this
        ImageButton btnForgotPassword = findViewById(R.id.btnforgotPassword);
        ImageButton btnDeleteAccount = findViewById(R.id.btnDeleteAccount);

        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        });

    }
}
