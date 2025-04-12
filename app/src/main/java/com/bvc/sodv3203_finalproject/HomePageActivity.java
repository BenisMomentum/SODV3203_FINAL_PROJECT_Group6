package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    ImageButton createWorkoutBtn, workoutRoutineBtn, searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        createWorkoutBtn = findViewById(R.id.create_workout_routine_homepage);
        workoutRoutineBtn = findViewById(R.id.workout_routine_btn);
        searchBtn = findViewById(R.id.searchBtn_homepage);

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
