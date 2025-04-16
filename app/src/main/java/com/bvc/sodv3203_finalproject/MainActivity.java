package com.bvc.sodv3203_finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        setStartupThemeMode();
        //This is a solution to a stupid problem...
        Utility.applicationContext = getApplicationContext();

        WorkoutData.getInstance().startUp(this);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //This is where we'd swap over to the login page.

        //Artificial delay.
        new CountDownTimer(1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //Does nothing. Should not do anything.
            }
            @Override
            public void onFinish(){

                switchToHome();
            }
        }.start();

    }

    private void setStartupThemeMode() {

        SharedPreferences pref = getSharedPreferences(SettingsActivity.PREF_SETTINGS_KEY, MODE_PRIVATE);

        //Gets dark mode. Default is Dark.
        boolean isDark = pref.getBoolean(SettingsActivity.PREF_DARKMODE_KEY, true);

        SettingsActivity.setDefaultNightMode(isDark);
    }

    //Moved to function for cleanliness
    private void switchToHome(){

        final Intent moveToHomePage = new Intent(this, HomePageActivity.class);

        startActivity(moveToHomePage);
    }

}