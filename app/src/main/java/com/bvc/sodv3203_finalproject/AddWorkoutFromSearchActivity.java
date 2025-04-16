package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.Adapters.AddRoutineAdapter;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

import org.json.JSONException;
import org.json.JSONObject;

public class AddWorkoutFromSearchActivity extends AppCompatActivity {

    public static final String WORKOUT_BUNDLE_KEY = "workout";

    protected RecyclerView routineView;

    protected Button btn_cancel;

    private Workout selectedWorkout;

    public EditText setsInput, repsInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworkout_fromsearch);

        setsInput = findViewById(R.id.AWFS_input_sets);
        repsInput = findViewById(R.id.AWFS_input_reps);

        routineView = findViewById(R.id.AWFS_routineView);
        btn_cancel = findViewById(R.id.AWFS_cancel);

        btn_cancel.setOnClickListener(v -> finish());

        try {
            selectedWorkout = Workout.fromJSON(new JSONObject(getIntent().getStringExtra(WORKOUT_BUNDLE_KEY)));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        setRoutineViewAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Sets these to the default of 0.

        setsInput.setText("0");
        repsInput.setText("0");
    }

    @Override
    protected void onStop() {
        super.onStop();
        WorkoutData.getInstance().saveData(this);
    }

    private void setRoutineViewAdapter() {

        AddRoutineAdapter adapter = new AddRoutineAdapter(this, selectedWorkout);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        routineView.setAdapter(adapter);
        routineView.setItemAnimator(new DefaultItemAnimator());
        routineView.setLayoutManager(manager);
    }

}
