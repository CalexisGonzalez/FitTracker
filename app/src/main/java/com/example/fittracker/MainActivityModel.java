package com.example.fittracker;

public class MainActivityModel implements LogInContract.Model {
    private UserBase lista;
    private final String MAIL_PRUEBA = "danko94.cg@gmail.com";
    private final String PASS_PRUEBA = "1234";

    public MainActivityModel() {

        this.lista = new UserBase();
        lista.addUsuario(new User(MAIL_PRUEBA, PASS_PRUEBA));
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
