package com.bvc.sodv3203_finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.Adapters.RoutineAdapter;
import com.bvc.sodv3203_finalproject.util.Utility;

import java.util.Objects;

/**
 * Our workout Log activity. AKA our Routine display that
 * shows all of the user's Workout Routines.
 *
 * workoutContainer is our Recycler view and thus our container.
 */
public class WorkoutLogActivity extends AppCompatActivity implements IGoBack {


    RecyclerView workoutContainer;

    //Other ImageButtons
    ImageButton addNewWorkout, backBtn;

    //Home bar imagebuttons
    ImageButton homeBtn, workoutsBtn, searchBtn, settingsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);

        addNewWorkout = findViewById(R.id.workout_btn_AddNew);

        backBtn = findViewById(R.id.btn_goBack);
        backBtn.setOnClickListener(view -> finish());

        //Recycler view handling
        workoutContainer = findViewById(R.id.workout_workoutContainer);
        setRoutineDisplayAdapter();

        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutsBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Utility.SetupHomeBarButtons(homeBtn, workoutsBtn, searchBtn, settingsBtn, this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();

        if(workoutContainer == null) return;

        //Refreshes the workout container with the latest data.
        //Please note: A full sync is necessary to ensure no errors with data being displayed.
        Objects.requireNonNull(workoutContainer.getAdapter()).notifyDataSetChanged();
    }

    /**
     * The button hook for our addNewWorkout button.
     *
     * Begins the process for adding a new workout routine.
     * @param view Required for button hook.
     */
    public void btnHook_AddWorkout(View view){
        Intent intent = new Intent(this, CreateWorkoutRoutineActivity.class);

        startActivity(intent);
    }

    /**
     * Sets the Routine Adapter to our recycler view.
     */
    private void setRoutineDisplayAdapter(){

        RoutineAdapter adapter = new RoutineAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        workoutContainer.setLayoutManager(manager);
        workoutContainer.setItemAnimator(new DefaultItemAnimator());
        workoutContainer.setAdapter(adapter);

    }

    @Override
    public void btn_GoBack(View view) {
        this.finish();
    }

}
