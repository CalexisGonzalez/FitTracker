package com.example.fittracker;

public class MainActivityModel implements LogInContract.Model {
    private final String MAIL_PRUEBA = "danko94.cg@gmail.com";
    private final String PASS_PRUEBA = "1234";
    private UserBase lista;

    public MainActivityModel() {
        lista = new UserBase();
        lista.addUsuario(new User(MAIL_PRUEBA, PASS_PRUEBA));
    }

    @Override
    public boolean isValidLogIn(String mail, String password) {
        for (User user : lista.getUsers()) {
            if (user.getmMail().equals(mail) && (user.getPassword().equals(password))) {
                return true;
            }
        }
        return false;
    }
}
