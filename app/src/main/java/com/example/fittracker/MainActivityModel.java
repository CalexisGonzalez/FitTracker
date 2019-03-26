package com.example.fittracker;

public class MainActivityModel implements LogInContract.Model {
    private UserBase lista;

    public MainActivityModel(UserBase list) {
        this.lista = list;
    }

    @Override
    public boolean isValidLogIn(User userInput) {
        for (User user : lista.getUsers()) {
            if (user.getMail().equals(userInput.getMail()) && (user.getPassword().equals(userInput.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}
