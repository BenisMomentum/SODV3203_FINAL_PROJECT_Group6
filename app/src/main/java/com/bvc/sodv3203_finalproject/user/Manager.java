package com.bvc.sodv3203_finalproject.user;

import android.widget.Toast;

import com.bvc.sodv3203_finalproject.LoginActivity;
import com.bvc.sodv3203_finalproject.SignUpActivity;

import org.jetbrains.annotations.NotNull;

public class Manager {

    protected User loggedInUser;

    public static Manager _instance = new Manager();

    private Manager(){

    }

    public static Manager get_instance() {
        return _instance;
    }

    public static boolean verifyUserCredentials(String username, String password){
        if(_instance.loggedInUser == null) return false;

        return username.equals(_instance.loggedInUser.getUsernameOrEmail()) && password.equals(_instance.loggedInUser.getPassword());
    }

    //To be used whenever an account is created (only in the SignUp_Activity class)
    public static void createAccount(String username, String password, SignUpActivity instance){
        if(!password.trim().equals(password)){

            Toast.makeText(instance, "Error: no spaces allowed in password", Toast.LENGTH_SHORT).show();
        }

        //After all verification is complete, we finally set the loggedInUser.
        //TODO: In case the user wishes to login with an old account, we need to store ALL iterations.

        _instance.loggedInUser = new User(username, password);
    }
}

//User class is here because we want to encapsulate it for security.
class User {

    private String usernameOrEmail;
    private String password;


    public User(@NotNull String usernameOrEmail, @NotNull String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
