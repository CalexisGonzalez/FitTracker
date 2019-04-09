package com.example.fittracker.DbQuery;

public interface DbGenericQuery<T, S> {
    public T executeQuery(S parameter);
}
