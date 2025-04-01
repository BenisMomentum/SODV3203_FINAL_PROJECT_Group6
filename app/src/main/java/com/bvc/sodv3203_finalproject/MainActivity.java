package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    public String Base_URL = "https://www.api-ninjas.com/api/exercises";
    public String API_URL_KEY = "BrcmuaWFWMgEDYFRX2rACA==GTJG6pXv69ECiGwA"; // API Key
    ImageButton btnHome;
    ImageButton btnWorkout;
    ImageButton btnSearch;
    ImageButton btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_logs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //for future implementation -> footer bar
        btnHome = findViewById(R.id.homeBtn_Home);
        btnWorkout = findViewById(R.id.homeBtn_workout);
        btnSearch = findViewById(R.id.homeBtn_search);
        btnSettings = findViewById(R.id.homeBtn_settings);


        //This is where we'd start synchronizing our persistent data from files.

        WorkoutData.getInstance().loadTestingData();


    }

    @Override
    protected void onStart() {
        super.onStart();

        //This is where we'd swap over to the login page.

        //Artificial delay.
//        new CountDownTimer(1000, 1000){
//            @Override
//            public void onTick(long millisUntilFinished) {
//                //Does nothing. Should not do anything.
//            }
//            @Override
//            public void onFinish(){
//
//                switchToLogin();
//            }
//        }.start();

    }

    //Moved to function for cleanliness
    private void switchToLogin() {

        final Intent moveToLogin = new Intent(this, LoginActivity.class);

        startActivity(moveToLogin);
    }

    public void btn_GoBack(View view) {
        finish();
    }


    //this is sample try for connecting API. I adapted Pedrp's code using resources



}

