package com.example.fittracker;

public class User {
    private String mMail;
    private String password;
    public User(String mail, String pass){
        this.mMail=mail;
        this.password=pass;
    }

    public String getmMail() {
        return mMail;
    }

    public String getPassword() {
        return password;
    }
}
