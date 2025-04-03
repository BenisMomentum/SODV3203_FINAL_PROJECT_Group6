package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

import java.util.List;

public class SearchWorkoutActivity extends AppCompatActivity {
    private EditText searchBar;
    private TextView searchResults;
    private Button searchButton, showAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_workout);

        searchBar = findViewById(R.id.search_SearchBar);
        searchResults = findViewById(R.id.workout_searches);
        searchButton = findViewById(R.id.settings_btn_search);
        showAllButton = findViewById(R.id.settings_btn_all);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchWorkouts();
            }
        });
        showAllButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showAllWorkouts();
            }
        });
        }

        private void searchWorkouts() {
        String query = searchBar.getText().toString();
        if(query.isEmpty()){
            searchResults.setText("Please enter your search");
            return;
        }
        List<Workout> results = WorkoutData.getInstance().searchWorkouts(query);
        displaySearchResults(results);
        }

        private void showAllWorkouts(){
        List<Workout> allWorkouts = WorkoutData.getInstance().getAllWorkouts();
        displaySearchResults(allWorkouts);
        }

        private void displaySearchResults(List<Workout> workouts){
        if(workouts.isEmpty()){
            searchResults.setText("No workouts found");
        }else{
            StringBuilder builder = new StringBuilder();
            for(Workout workout : workouts) {
                builder.append(workout.getName()).append("\n");
            }
            searchResults.setText(builder.toString());
        }


    }

}
