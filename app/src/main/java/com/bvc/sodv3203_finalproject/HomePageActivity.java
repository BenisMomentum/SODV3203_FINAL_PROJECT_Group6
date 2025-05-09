package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.INavigation;

/**
 * Our home page activity.
 *
 * Note: Utility.SetupHomeBarButtons() is not used as we need all of these buttons active.
 */
public class HomePageActivity extends AppCompatActivity implements INavigation {

    ImageButton createWorkoutBtn, workoutRoutineBtn, searchBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Navigation button instantiation
        createWorkoutBtn = findViewById(R.id.home_ibtn_createWorkout);
        workoutRoutineBtn = findViewById(R.id.home_ibtn_workout);
        searchBtn = findViewById(R.id.home_ibtn_search);
        settingsBtn = findViewById(R.id.home_ibtn_settings);

        createWorkoutBtn.setOnClickListener(v -> navigateTo(CreateWorkoutRoutineActivity.class));
        workoutRoutineBtn.setOnClickListener(v -> navigateTo(WorkoutLogActivity.class));
        searchBtn.setOnClickListener(v -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(v -> navigateTo(SettingsActivity.class));
    }

    @Override
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(HomePageActivity.this, activityClass);
        startActivity(intent);
    }
}
