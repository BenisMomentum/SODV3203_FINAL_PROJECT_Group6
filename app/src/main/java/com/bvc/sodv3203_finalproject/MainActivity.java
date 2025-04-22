package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

/**
 * This is our launch page, effectively. It's where we startup our data
 * and then transition to the home page.
 */
public class MainActivity extends AppCompatActivity implements INavigation {

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

        WorkoutData.getInstance().startUp(getApplicationContext());
    }

    @Override
    protected void onResume(){
        super.onResume();

        //This is where we'd swap over to the login page.

        //Artificial delay.
        new CountDownTimer(500, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //Does nothing. Should not do anything.
            }
            @Override
            public void onFinish(){
                navigateTo(HomePageActivity.class);
            }
        }.start();
    }

    /**
     * Sets up our theme mode upon startup.
     */
    private void setStartupThemeMode() {

        SharedPreferences pref = getSharedPreferences(SettingsActivity.PREF_SETTINGS_KEY, MODE_PRIVATE);

        //Gets dark mode. Default is Dark.
        boolean isDark = pref.getBoolean(SettingsActivity.PREF_DARKMODE_KEY, Utility.GetSystemIsDark(this));

        SettingsActivity.setDefaultNightMode(isDark);
    }

    /**
     * Switches to the home page once we've loaded everything.
     *
     * Note: This was made into a function for cleanliness.
     */
    @Override
    public void navigateTo(Class<?> activityClass) {
        final Intent swapToHome = new Intent(this, activityClass);

        startActivity(swapToHome);
    }


}