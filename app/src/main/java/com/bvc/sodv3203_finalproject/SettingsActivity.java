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
        darkModeSwitch = findViewById(R.id.settings_darkModeSwitch);


        //used for dark_mode
        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDarkModeOn = sharedPreferences.getBoolean("dark_mode", false);

        setDefaultNightMode(isDarkModeOn);

        darkModeSwitch.setChecked(isDarkModeOn);

        //change the theme when switch btn is active
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            setDefaultNightMode(isChecked);

            editor.putBoolean("dark_mode", isChecked);

            editor.apply();

            //initialize the activity to apply the theme change
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });


        //not too sure about this
        ImageButton btnForgotPassword = findViewById(R.id.settings_btn_forgotPwd);
        ImageButton btnDeleteAccount = findViewById(R.id.settings_btn_DelAcc);

        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        });

    }

    //Function made to keep code DRY
    private void setDefaultNightMode(boolean isModeNight){
        if(isModeNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
