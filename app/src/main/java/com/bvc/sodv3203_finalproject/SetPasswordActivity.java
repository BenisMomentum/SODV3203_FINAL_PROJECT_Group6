package com.bvc.sodv3203_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetPasswordActivity extends AppCompatActivity {

    public EditText newPassword, confirmPassword;
    public Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        newPassword = findViewById(R.id.setPwd_confirm);
        confirmPassword = findViewById(R.id.setPwd_confirm);
        resetBtn = findViewById(R.id.forgot_btn_Submit);



        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    // I still need to create validation for the password being matched or not.

    //having a problem when I press reset it crashes. I need to figure it out.

    public void reset() {
        if (newPassword != null && confirmPassword != null) {
            newPassword.setText("");
            confirmPassword.setText("");
            Toast.makeText(SetPasswordActivity.this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error resetting fields", Toast.LENGTH_LONG).show();
        }
    }

    public void reset_Btn(View view) {
        reset();
    }
}