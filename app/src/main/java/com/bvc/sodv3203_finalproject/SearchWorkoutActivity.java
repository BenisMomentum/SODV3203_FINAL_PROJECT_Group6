package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.Adapters.SearchWorkoutAdapter;
import com.bvc.sodv3203_finalproject.util.APICaller;
import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.Workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkoutActivity extends AppCompatActivity implements INavigation, IGoBack {
    private EditText searchBar;

    private RecyclerView searchResults;

    private Button searchButton;
    private ImageButton backBtn, homeBtn, workoutBtn, searchBtn, settingsBtn;

    public List<Workout> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_workout);

        //Standard instantiation
        this.searchBar = findViewById(R.id.search_SearchBar);
        this.searchResults = findViewById(R.id.search_cont_SearchResults);

        backBtn = findViewById(R.id.btn_goBack);
        backBtn.setOnClickListener(this::btn_GoBack);

        searchButton = findViewById(R.id.settings_btn_search);
        searchButton.setOnClickListener(this::processSearch);

        setAdapterForSearchResults();

        //Navigation buttons
        homeBtn = findViewById(R.id.homeBtn_Home);
        workoutBtn = findViewById(R.id.homeBtn_workout);
        searchBtn = findViewById(R.id.homeBtn_search);
        settingsBtn = findViewById(R.id.homeBtn_settings);

        homeBtn.setOnClickListener(view -> navigateTo(HomePageActivity.class));
        workoutBtn.setOnClickListener(view -> navigateTo(WorkoutLogActivity.class));
        searchBtn.setOnClickListener(view -> navigateTo(SearchWorkoutActivity.class));
        settingsBtn.setOnClickListener(view -> navigateTo(SettingsActivity.class));
    }

    private void setAdapterForSearchResults() {

        SearchWorkoutAdapter adapter = new SearchWorkoutAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        searchResults.setAdapter(adapter);
        searchResults.setItemAnimator(new DefaultItemAnimator());
        searchResults.setLayoutManager(manager);

    }

    public void processSearch(View view){

        String query = searchBar.getText().toString().trim();

        if(query.isBlank()){
            Utility.displayMsg(this, "Cannot search for nothing!", false);

            return;
        }

        //Gets the exercise from the API.
        JSONObject source = APICaller.getExercise(query);

        try {
            JSONArray dataArr = source.getJSONArray(APICaller.API_RET_KEY);
            Workout w = null;

            for(int i = 0; i < dataArr.length(); i++){

                w = Workout.ParseFromAPI(dataArr.getJSONObject(i));

                resultList.add(w);

            }

            searchResults.getAdapter().notifyDataSetChanged();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

//------------------------------------------------footer btns
    public void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(SearchWorkoutActivity.this, activityClass);
        startActivity(intent);
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish();
    }


}


