package com.bvc.sodv3203_finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.util.IGoBack;
import com.bvc.sodv3203_finalproject.util.INavigation;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CreateWorkoutRoutineActivity extends AppCompatActivity implements IGoBack {

    public EditText nameInput;

    //The sequencing for this is as follows:
    //Sun, Mon, Tue, Wed, Thu, Fri, Sat
    public CheckBox[] days;
    public Button submit;

    public ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workoutroutine);


        nameInput = findViewById(R.id.CWR_ET_routineName);

        //We use an array here as it would make
        //indexing easier and faster.
        days = new CheckBox[]{
            findViewById(R.id.CWR_CB_Sun),
            findViewById(R.id.CWR_CB_Mon),
            findViewById(R.id.CWR_CB_Tue),
            findViewById(R.id.CWR_CB_Wed),
            findViewById(R.id.CWR_CB_Thu),
            findViewById(R.id.CWR_CB_Fri),
            findViewById(R.id.CWR_CB_Sat)
        };

        //Submit button handling
        submit = findViewById(R.id.CWR_btn_submit);
        submit.setOnClickListener(this::AddWorkoutToData);

        //Back button handling
        backBtn = findViewById(R.id.btn_goBack);
        backBtn.setOnClickListener(view -> finish());

    }

    @SuppressLint("NewApi")
    public void AddWorkoutToData(View view){

        String name = Utility.getText(nameInput);

        //Since we'll be mostly searching by name as opposed to ID, we cannot have a duplicate name.
        if(WorkoutData.getInstance().hasName(name)){
            Utility.displayMsg(this, Utility.getErrorMessage(this, R.string.ErrorMessage_duplicateRoutineNames), false);

            return;
        }

        //In short, the below code loops through the days
        //and registers each day the user has selected
        //then converts it back to a primitive array.

        //This is the easiest way I could find.

        List<DayOfWeek> workoutDays = new ArrayList<>();

        for(int i = 0; i < days.length; i++){
            if(days[i].isChecked()){
                String day = days[i].getText().toString();
                workoutDays.add(Utility.getDay(day));
            }
        }

        WorkoutRoutine routine = new WorkoutRoutine(name, workoutDays.toArray(new DayOfWeek[workoutDays.size()]));

        WorkoutData.getInstance().add(routine);

        WorkoutData.getInstance().saveData(this);
        this.finish();
    }

    @Override
    public void btn_GoBack(View view) {
        this.finish();
    }

}
