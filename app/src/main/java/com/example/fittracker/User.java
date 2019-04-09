package com.example.fittracker;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = ConstantUtils.DB_USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String mail;
    private String password;
    private String name;
    private String surname;

    public User(String mail, String password, String name, String surname) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Ignore
    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public void setId(Integer id){ this.id = id;}

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
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
