package com.example.fittracker;

import org.junit.Before;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LogInTest {
    private @Mock UserBase base;
    private @Mock User user;
    private MainActivityModel model;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        model = new MainActivityModel(base);
        List<User>users = new ArrayList<>();
        users.add(user);
        when(base.getUsers()).thenReturn(users);
    }

    @Test
    public void shouldAuthenticateUser() throws Exception {
        when(user.getmMail()).thenReturn("mockUsername");
        when(user.getPassword()).thenReturn("mockPassword");
        assertTrue(model.isValidLogIn("mockUsername", "mockPassword"));
    }
    @Test
    public void shouldNotAuthenticateUser() throws Exception {
        when(user.getmMail()).thenReturn("mockUsername");
        when(user.getPassword()).thenReturn("mockPassword");
        assertFalse(model.isValidLogIn("username", "password"));
    }

}
