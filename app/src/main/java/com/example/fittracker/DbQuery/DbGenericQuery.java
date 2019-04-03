package com.example.fittracker.DbQuery;

import com.example.fittracker.User;

public interface DbGenericQuery<T> {
    public T executeQuery(User user);
}
