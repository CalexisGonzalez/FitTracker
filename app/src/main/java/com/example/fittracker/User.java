package com.example.fittracker;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    private String mMail;
    private String password;
    private String name;
    private String surname;

    public User(String mail, String password, String name, String surname) {
        this.mMail = mail;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getMail() {
        return mMail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
