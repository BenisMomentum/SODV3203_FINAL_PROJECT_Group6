package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

import java.util.List;

public class SearchWorkoutActivity extends AppCompatActivity implements INavigation {
    private EditText searchBar;
    private TextView searchResults;
    private Button searchButton, showAllButton;
    ImageButton backBtn, homeBtn, workoutBtn, searchBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_workout);

        searchBar = findViewById(R.id.search_SearchBar);
        searchResults = findViewById(R.id.workout_searches);
        searchButton = findViewById(R.id.settings_btn_search);
        showAllButton = findViewById(R.id.settings_btn_all);
        backBtn = findViewById(R.id.btn_goBack);
        //------------------------------------------------footer btns
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);

        homeBtn.setOnClickListener(view -> navigateTo(MainActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(MainActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(view -> navigateTo(SettingsActivity.class));
        //--

        searchButton.setOnClickListener(this::searchWorkouts);
        showAllButton.setOnClickListener(this::showAllWorkouts);
        backBtn.setOnClickListener(view -> finish());
    }





    public void searchWorkouts(View view) {
        String query = searchBar.getText().toString();
        if(query.isEmpty()){
            searchResults.setText("Please enter your search");
            return;
        }
        List<Workout> results = WorkoutData.getInstance().searchWorkouts(query);
        displaySearchResults(results);
    }

    public void showAllWorkouts(View view){
        List<Workout> allWorkouts = WorkoutData.getInstance().getAllWorkouts();
        displaySearchResults(allWorkouts);
    }

    private void displaySearchResults(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            searchResults.setText("No workouts found");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Workout workout : workouts) {
                builder.append(workout.getName()).append("\n");
            }
            searchResults.setText(builder.toString());
        }
    }
//------------------------------------------------footer btns
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(SearchWorkoutActivity.this, activityClass);
        startActivity(intent);
    }


}


