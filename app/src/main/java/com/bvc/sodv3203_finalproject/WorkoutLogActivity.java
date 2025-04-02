package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

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

    public void loadWorkoutData(){

        List<WorkoutRoutine> routines = WorkoutData.getInstance().routines();

        for(int i = 0; i < routines.size(); i++){

            LinearLayout widget = WorkoutFactory.createWidget(routines.get(i), this);

            try {
                workoutContainer.addView(widget, i, widget.getLayoutParams());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }

        workoutContainer.postInvalidate();
    }

    public void WORKOUT_DEBUG_ADD(View view){

        WorkoutData.getInstance().add(WorkoutData.getInstance().get(0));

        workoutContainer.removeAllViews();
        loadWorkoutData();

    }

}
