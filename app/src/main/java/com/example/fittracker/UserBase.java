package com.example.fittracker;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
    private List<User> users;
    public UserBase(){
        users = new ArrayList<>();
    }
    public void addUsuario(User usuario){
        users.add(usuario);
    }
    public List<User> getUsers(){
        return users;
    }
}
