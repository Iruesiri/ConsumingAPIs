package com.example.crudapplication.callbacks;

import android.content.Context;
import android.content.Intent;

import com.example.crudapplication.MainActivity;
import com.example.crudapplication.model.LoginDetails;

public class SharedPreferenceClass {
    android.content.SharedPreferences preferences;
    android.content.SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String USER_ID = "id";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";
    //private static final String CHECKBOX = "check";
    private static final String LOGIN = "isLoggedIn";

    public SharedPreferenceClass(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(TOKEN, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void createLoginSession(String token, String username, String id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(TOKEN, token);
        editor.putString(USER_NAME, username);
        editor.putString(USER_ID, id);
        //editor.putString(CHECKBOX, check);
        editor.commit();
    }

    public void endLoginSession() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public LoginDetails getUserDetail() {
        LoginDetails details = new LoginDetails();
        details.setUsername(preferences.getString(USER_NAME, ""));
        //details.setPassword(preferences.getString(PASSWORD, ""));
        details.setToken(preferences.getString(TOKEN, ""));
        return details;
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(LOGIN, false);
    }
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
