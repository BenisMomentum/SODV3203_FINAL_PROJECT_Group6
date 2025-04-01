package com.bvc.sodv3203_finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //This is where we'd start synchronizing our persistent data from files.

        WorkoutData.getInstance().loadTestingData();
    }

    @Override
    protected void onStart(){
        super.onStart();

        //This is where we'd swap over to the login page.

        //Artificial delay.
        new CountDownTimer(1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //Does nothing. Should not do anything.
            }
            @Override
            public void onFinish(){

                switchToLogin();
            }
        }.start();

    }

    //Moved to function for cleanliness
    private void switchToLogin(){

        final Intent moveToLogin = new Intent(this, WorkoutLogActivity.class);

        startActivity(moveToLogin);
    }

    public void btn_GoBack(View view){
        finish();
    }
}