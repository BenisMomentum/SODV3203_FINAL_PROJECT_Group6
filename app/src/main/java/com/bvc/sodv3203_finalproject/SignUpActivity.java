package com.bvc.sodv3203_finalproject;

import android.os.Bundle;

import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {


    EditText fullName, password, passwordConfirm, usernameorEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_accout_page);


        fullName = findViewById(R.id.createAccount_FullName_Input);
        usernameorEmail = findViewById(R.id.createAccount_emailOrUsername_input);
        password = findViewById(R.id.createAccount_password_input);
        passwordConfirm = findViewById(R.id.createAcount_passwordConfirm_input);
    }

}
