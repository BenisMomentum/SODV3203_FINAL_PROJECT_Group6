package com.bvc.sodv3203_finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.Adapters.EditWorkoutAdapter;
import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class EditWorkoutRoutineActivity extends AppCompatActivity implements IGoBack {

    private EditText newName;

    //Sequence:
    //Sun, Mon, Tue, Wed, Thu, Fri, Sat
    public CheckBox[] newDays;

    public ImageButton backBtn;

    public RecyclerView routineWorkouts = null;
    public WorkoutRoutine editedRoutine = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workoutroutine);

        loadRoutineToEdit();

        backBtn = findViewById(R.id.btn_goBack);
        backBtn.setOnClickListener(this::btn_GoBack);

        this.newName = findViewById(R.id.EWR_ET_routineName);

        this.newDays = new CheckBox[]{
                findViewById(R.id.EWR_CB_Sun),
                findViewById(R.id.EWR_CB_Mon),
                findViewById(R.id.EWR_CB_Tue),
                findViewById(R.id.EWR_CB_Wed),
                findViewById(R.id.EWR_CB_Thu),
                findViewById(R.id.EWR_CB_Fri),
                findViewById(R.id.EWR_CB_Sat)
        };

        this.routineWorkouts = findViewById(R.id.EWR_cont_r_workout);
        loadRoutineWorkoutAdapter();

    }

    /**
     * Just loads the EditWorkoutAdapter for this activity.
     */
    private void loadRoutineWorkoutAdapter() {
        EditWorkoutAdapter adapter = new EditWorkoutAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());


        routineWorkouts.setLayoutManager(manager);
        routineWorkouts.setItemAnimator(new DefaultItemAnimator());
        routineWorkouts.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(editedRoutine != null){
            loadRoutineData();
        }

    }

    /**
     * Loads the Routine to edit from the intent extras.
     *
     * Note: the intent was much more intuitive to implement over the Bundle.
     */
    public void loadRoutineToEdit(){
        try {
            if(getIntent().getExtras() == null) throw new IllegalStateException("ERR: DATA WAS NOT PASSED\n\n");

            String routineData = this.getIntent().getStringExtra(Utility.EDIT_WORKOUT_BUNDLE_KEY);

            this.editedRoutine = WorkoutRoutine.fromJSON(new JSONObject(routineData));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRoutineData() {

        newName.setText(editedRoutine.name);

        setCheckBoxValues(editedRoutine.workoutDays);

        Log.d(Utility.DEBUG_CODE, editedRoutine.toJSON().toString());

    }

    @SuppressLint("NewApi")
    private void setCheckBoxValues(DayOfWeek[] workoutDays) {

        if(workoutDays == null) return;

        if(workoutDays.length == 0) return;

        //TLDR: Uses a switch-case to determine which checkboxes should be checked on launch.
        int i = 0;

        while(i < workoutDays.length){
            switch(workoutDays[i]){
                case SUNDAY:
                    this.newDays[0].setChecked(true);
                    break;
                case MONDAY:
                    this.newDays[1].setChecked(true);
                    break;
                case TUESDAY:
                    this.newDays[2].setChecked(true);
                    break;
                case WEDNESDAY:
                    this.newDays[3].setChecked(true);
                    break;
                case THURSDAY:
                    this.newDays[4].setChecked(true);
                    break;
                case FRIDAY:
                    this.newDays[5].setChecked(true);
                    break;
                case SATURDAY:
                    this.newDays[6].setChecked(true);
                    break;
            }
            i++;
        }

    }


    @SuppressLint("NewApi")
    public void submit(View view){

        String name = Utility.getText(newName);

        //Since we'll be mostly searching by name as opposed to ID, we cannot have a duplicate name.

        List<DayOfWeek> workoutDays = new ArrayList<>();

        for(int i = 0; i < newDays.length; i++){
            if(newDays[i].isChecked()){

                String day = newDays[i].getText().toString();
                workoutDays.add(Utility.getDay(day));
            }
        }

        //We get the index in order to directly set the new routine using the singleton
        int index = WorkoutData.getInstance().indexOf(editedRoutine.name);

        WorkoutData.getInstance().routines().set(index,
                new WorkoutRoutine(name, editedRoutine.workouts, workoutDays.toArray(new DayOfWeek[0]))
        );

        WorkoutData.getInstance().saveData(this);
        this.finish();
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish();
    }
}
