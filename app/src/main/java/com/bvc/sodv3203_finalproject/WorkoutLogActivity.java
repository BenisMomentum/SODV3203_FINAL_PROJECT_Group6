package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.util.List;

public class WorkoutLogActivity extends AppCompatActivity implements INavigation {


    LinearLayout workoutContainer;

    ImageButton addNewWorkout, homeBtn, workoutBtn, searchBtn, settingsBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);

        workoutContainer = findViewById(R.id.workout_workoutContainer);
        addNewWorkout = findViewById(R.id.workout_btn_AddNew);
        //------------------------------------------------footer btns
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);
        backBtn = findViewById(R.id.btn_goBack);

        homeBtn.setOnClickListener(view -> navigateTo(MainActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(WorkoutLogActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(view -> navigateTo(SettingsActivity.class));
        backBtn.setOnClickListener(view -> finish());


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
    //------------------------------------------------
    //to have the footer image buttons functional
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(WorkoutLogActivity.this, activityClass);
        startActivity(intent);
    }

}
