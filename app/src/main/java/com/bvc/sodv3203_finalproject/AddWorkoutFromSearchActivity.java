package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.Adapters.AddSearchWorkoutToRoutineAdapter;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * From this activity, we add our selected workout to one of the routines the user has chosen.
 */
public class AddWorkoutFromSearchActivity extends AppCompatActivity {

    public static final String WORKOUT_INTENT_EXTRA_KEY = "workout";

    //Used for when the user wants to make a new routine with this workout
    public static final String ADDNEW_INTENT_EXTRA_KEY = "add_to_new_routine";

    //Container for the routines.
    protected RecyclerView routineView;

    protected Button btn_cancel, btn_addNewRoutine;

    //We use this to reference the workout selected by the user
    //in SearchWorkoutActivity.java
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

        btn_addNewRoutine = findViewById(R.id.AWFS_AddNewRoutine);
        btn_addNewRoutine.setOnClickListener(this::addNewRoutine);

        //SHOULD never error.
        try {
            selectedWorkout = Workout.fromJSON(new JSONObject(getIntent().getStringExtra(WORKOUT_INTENT_EXTRA_KEY)));
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

    protected void addNewRoutine(View view){

        //Fixes bug where user is allowed to add 0 set and 0 rep workout when clicking this
        //No need for parsing, we don't want to use extra resources for this.
        if(Utility.getText(setsInput).equals("0") || Utility.getText(repsInput).equals("0")){
            Utility.displayMsg(this, Utility.getErrorMessage(this, R.string.ErrMsg_setsOrRepsAre0), false);

            return;
        }

        final Intent newRoutine = new Intent(this, CreateWorkoutRoutineActivity.class);

        selectedWorkout.sets = Integer.parseInt(Utility.getText(setsInput));
        selectedWorkout.reps = Integer.parseInt(Utility.getText(repsInput));

        newRoutine.putExtra(ADDNEW_INTENT_EXTRA_KEY, selectedWorkout.toJSON().toString());

        startActivity(newRoutine);
        this.finish();
    }

    private void setRoutineViewAdapter() {

        AddSearchWorkoutToRoutineAdapter adapter = new AddSearchWorkoutToRoutineAdapter(this, selectedWorkout);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        routineView.setAdapter(adapter);
        routineView.setItemAnimator(new DefaultItemAnimator());
        routineView.setLayoutManager(manager);
    }

}
