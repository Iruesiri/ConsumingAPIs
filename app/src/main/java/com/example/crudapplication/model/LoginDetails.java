package com.example.crudapplication.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.crudapplication.BR;

public class LoginDetails extends BaseObservable {
    private String username;
    private String password;
    private String token;

    public LoginDetails(String username, String password){
        this.username = username;
        this.password = password;
    }

    public LoginDetails() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
