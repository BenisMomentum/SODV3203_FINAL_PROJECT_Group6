package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.util.List;

public class WorkoutLogActivity extends AppCompatActivity {


    public LinearLayout workoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);

        workoutContainer = findViewById(R.id.workout_workoutContainer);

        workoutContainer.removeAllViewsInLayout();

        loadWorkoutData();
    }

    @Override
    protected void onStart(){
        super.onStart();

    }


    private void loadWorkoutData(){

        List<WorkoutRoutine> routines = WorkoutData.getInstance().routines();

        Log.d("loadWorkoutData", "DATA LENGTH: " + WorkoutData.getInstance().length());

        for(int i = 0; i < routines.size(); i++){

            workoutContainer.addView(WorkoutFactory.createWidget(routines.get(i), this));

        }


    }

}
