package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.util.List;

public class WorkoutLogActivity extends AppCompatActivity {


    protected LinearLayout workoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        workoutContainer = findViewById(R.id.workout_workoutContainer);

    }

    @Override
    protected void onStart(){
        super.onStart();

        workoutContainer.removeAllViews();
        loadWorkoutData();
    }


    private void loadWorkoutData(){

        List<WorkoutRoutine> routines = WorkoutData.getInstance().routines();

        for(int i = 0; i < routines.size(); i++){

            workoutContainer.addView(WorkoutFactory.createWidget(routines.get(i), workoutContainer.getContext()));

        }

    }

}
