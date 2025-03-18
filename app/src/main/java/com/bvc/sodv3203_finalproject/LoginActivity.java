package com.bvc.sodv3203_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activty_login);

        signUp = findViewById(R.id.loginBtn_SignUp);

        signUp.setOnClickListener(this::hook_SignUp);

        //This is where we'd start synchronizing our persistent data from files.
    }

    //BUTTON HANDLING

    public void hook_SignUp(View view){

        Intent moveToSignUp = new Intent(this, SignUpActivity.class);

        startActivity(moveToSignUp);
    }

}
