package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.Adapters.RoutineAdapter;

public class WorkoutLogActivity extends AppCompatActivity implements INavigation, IGoBack {


    RecyclerView workoutContainer;

    ImageButton addNewWorkout, homeBtn, workoutBtn, searchBtn, settingsBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);


        addNewWorkout = findViewById(R.id.workout_btn_AddNew);

        // Bottom Navigation Buttons

        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);
        backBtn = findViewById(R.id.btn_goBack);

        homeBtn.setOnClickListener(view -> navigateTo(HomePageActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(WorkoutLogActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(view -> navigateTo(SettingsActivity.class));
        backBtn.setOnClickListener(view -> finish());

        //Recycler view handling
        workoutContainer = findViewById(R.id.workout_workoutContainer);
        setRoutineDisplayAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(workoutContainer == null) return;

        //Will need adapting for editing a workout routine.
        //PS. I know it's deprecated. BUT SCREW THE RULES, I HAVE MONEY.
        workoutContainer.getAdapter().notifyDataSetChanged();
    }

    public void btnHook_AddWorkout(View view){
        Intent intent = new Intent(this, CreateWorkoutRoutineActivity.class);

        startActivity(intent);
    }

    private void setRoutineDisplayAdapter(){

        RoutineAdapter adapter = new RoutineAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        workoutContainer.setLayoutManager(manager);
        workoutContainer.setItemAnimator(new DefaultItemAnimator());
        workoutContainer.setAdapter(adapter);

    }

    //------------------------------------------------
    //to have the footer image buttons functional
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(WorkoutLogActivity.this, activityClass);
        startActivity(intent);
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish();
    }

}
