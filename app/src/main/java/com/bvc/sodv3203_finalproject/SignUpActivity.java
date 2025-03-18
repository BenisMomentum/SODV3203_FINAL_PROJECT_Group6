package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.user.Manager;
import com.bvc.sodv3203_finalproject.util.Utility;

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


    // BUTTON HOOK METHODS
    public void btn_createAccount(View view){
        if(!Utility.getText(password).equals(Utility.getText(passwordConfirm))){

            Utility.displayMsg(this, "Error: Passwords do not match", true);

            return;
        }

        //Creates the account
        Manager.createAccount(Utility.getText(fullName), Utility.getText(usernameorEmail), Utility.getText(password));
    }

}
