package com.example.fittracker;

public class MainActivityModel implements LogInContract.Model{
    private UserBase listUsuarios;
    public MainActivityModel (UserBase listUsuarios){
        this.listUsuarios = listUsuarios;
    }

    @Override
    public boolean isValidLogIn(String mail, String password) {
        for (User user:listUsuarios.getUsers()){
            if (user.getmMail().equals(mail) && (user.getPassword().equals(password))){
                return true;
            }
        }
        return false;
    }
}
