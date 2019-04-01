package com.example.fittracker.DbQueries;

import com.example.fittracker.User;

public interface DbBooleanQuery<T> {
    public T executeQuery(User user);
}
