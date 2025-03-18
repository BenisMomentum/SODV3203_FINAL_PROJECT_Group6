package com.bvc.sodv3203_finalproject.user;

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

        return username.equals(_instance.loggedInUser.getEmail_or_phone()) && password.equals(_instance.loggedInUser.getPassword());
    }

    //To be used whenever an account is created (only in the SignUp_Activity class)
    public static void createAccount(String fullName, String username, String password){

        //After all verification is complete, we finally set the loggedInUser.
        //TODO: In case the user wishes to login with an old account, we need to store ALL iterations.

        _instance.loggedInUser = new User(fullName, username, password);
    }
}

//User class is here because we want to encapsulate it for security.
class User {

    private String fullName;
    private String email_or_phone;
    private String password;


    public User(@NotNull String fullName, @NotNull String email_or_phone, @NotNull String password) {
        this.fullName = fullName;
        this.email_or_phone = email_or_phone;
        this.password = password;
    }

    public String getEmail_or_phone() {
        return email_or_phone;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmail_or_phone(String email_or_phone) {
        this.email_or_phone = email_or_phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
