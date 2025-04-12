package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    ImageButton createWorkoutBtn, workoutRoutineBtn, searchBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        createWorkoutBtn = findViewById(R.id.home_ibtn_createWorkout);
        workoutRoutineBtn = findViewById(R.id.home_ibtn_workout);
        searchBtn = findViewById(R.id.home_ibtn_search);
        settingsBtn = findViewById(R.id.home_ibtn_settings);

        // Exemplo de navegação ao clicar no botão
        createWorkoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, CreateWorkoutRoutineActivity.class);
            startActivity(intent);
        });

        workoutRoutineBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, WorkoutLogActivity.class);
            startActivity(intent);
        });

        searchBtn.setOnClickListener(v -> {
                Intent intent = new Intent(HomePageActivity.this, SearchWorkoutActivity.class);
            startActivity(intent);
        });
    }
}
