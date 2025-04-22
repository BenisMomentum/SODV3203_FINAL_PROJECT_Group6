package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.Adapters.WorkoutModeAdapter;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This activity is what drives our Workout Mode. The workoutView RecyclerView is meant
 * to display all of the user's workouts in a convenient format. To see where that's done
 * please look at Adapters/WorkoutModeAdapter.java
 */
public class WorkoutModeActivity extends AppCompatActivity {

    private Button exitBtn;

    public WorkoutRoutine selectedRoutine;

    public RecyclerView workoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_mode);

        loadSelectedRoutine();

        exitBtn = findViewById(R.id.WM_btn_exit);
        exitBtn.setOnClickListener(v -> finish());

        workoutView = findViewById(R.id.WM_routineContainer);

        loadWorkoutViewAdapter();
    }


    private void loadWorkoutViewAdapter() {

        WorkoutModeAdapter adapter = new WorkoutModeAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        workoutView.setAdapter(adapter);
        workoutView.setItemAnimator(new DefaultItemAnimator());
        workoutView.setLayoutManager(manager);
    }

    /**
     * Gets the selected routine from the intent's string extra.
     *
     * Note: While I could have passed it in a bundle, doing it via
     * the intent was more intuitive.
     */
    public void loadSelectedRoutine(){
        try {

            if(getIntent().getExtras() == null) throw new IllegalStateException("ERR: DATA WAS NOT PASSED\n\n");

            String routineData = this.getIntent().getStringExtra(Utility.WORKOUT_MODE_BUNDLE_KEY);

            WorkoutRoutine routineRef = WorkoutRoutine.fromJSON(new JSONObject(routineData));

            this.selectedRoutine = WorkoutData.getInstance().get(routineRef);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
