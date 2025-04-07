package com.bvc.sodv3203_finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.RoutineAdapter;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutRoutineActivity extends AppCompatActivity {

    public EditText nameInput;
    public CheckBox[] days;
    public Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workoutroutine);


        nameInput = findViewById(R.id.CWR_ET_routineName);

        days = new CheckBox[]{
            findViewById(R.id.CWR_CB_Sun),
            findViewById(R.id.CWR_CB_Mon),
            findViewById(R.id.CWR_CB_Tue),
            findViewById(R.id.CWR_CB_Wed),
            findViewById(R.id.CWR_CB_Thu),
            findViewById(R.id.CWR_CB_Fri),
            findViewById(R.id.CWR_CB_Sat)
        };

        submit = findViewById(R.id.CWR_btn_submit);
        submit.setOnClickListener(this::AddWorkoutToData);

    }

    @SuppressLint("NewApi")
    public void AddWorkoutToData(View view){

        String name = nameInput.getText().toString().trim();

        //Since we'll be mostly searching by name as opposed to ID, we cannot have a duplicate name.
        if(WorkoutData.getInstance().hasName(name)){
            Utility.displayMsg(this, "Cannot have routines with the same name. Please change it.", false);

            return;
        }

        List<DayOfWeek> workoutDays = new ArrayList<>();

        for(int i = 0; i < days.length; i++){
            if(days[i].isChecked()){

                String day = days[i].getText().toString();
                workoutDays.add(Utility.getDay(day));
            }
        }

        WorkoutRoutine routine = new WorkoutRoutine(name, workoutDays.toArray(new DayOfWeek[workoutDays.size()]));

        WorkoutData.getInstance().add(routine);

        Log.d("CreateWorkoutRoutineActivity", routine.name);



        this.finish();
    }

}
