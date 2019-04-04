package com.example.fittracker.DbQuery;

import com.example.fittracker.User;

public interface DbGenericQuery<T,S> {
    public T executeQuery(S parameter);
}
