package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.APICaller;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import org.json.JSONException;

import java.util.List;

public class WorkoutLogActivity extends AppCompatActivity {


    LinearLayout workoutContainer;

    ImageButton addNewWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);

        workoutContainer = findViewById(R.id.workout_workoutContainer);
        addNewWorkout = findViewById(R.id.workout_btn_AddNew);

        addNewWorkout.setOnClickListener(this::WORKOUT_DEBUG_ADD);


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

            workoutContainer.addView(WorkoutFactory.createWidget(routines.get(i), this));

        }

        workoutContainer.postInvalidate();
    }

    public void WORKOUT_DEBUG_ADD(View view){

        workoutContainer.addView(WorkoutFactory.createWidget(WorkoutData.getInstance().get(0), this));

    }

}
