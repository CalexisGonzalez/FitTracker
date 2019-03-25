package com.example.fittracker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
    private final String MOCK_USER = "mockUsername";
    private final String MOCK_PASS = "mockPassword";
    private final String MOCK_FALSE_USER = "username";
    private final String MOCK_FALSE_PASS = "password";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new MainActivityModel(base);
        List<User> users = new ArrayList<>();
        users.add(user);
        when(base.getUsers()).thenReturn(users);
    }

    @Test
    public void shouldAuthenticateUser() throws Exception {
        when(user.getmMail()).thenReturn(MOCK_USER);
        when(user.getPassword()).thenReturn(MOCK_PASS);
        assertTrue(model.isValidLogIn(MOCK_USER, MOCK_PASS));
    }

    @Test
    public void shouldNotAuthenticateUser() throws Exception {
        when(user.getmMail()).thenReturn(MOCK_USER);
        when(user.getPassword()).thenReturn(MOCK_PASS);
        assertFalse(model.isValidLogIn(MOCK_FALSE_USER, MOCK_FALSE_PASS));
    }

}
