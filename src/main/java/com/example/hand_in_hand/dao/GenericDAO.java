package com.example.hand_in_hand.dao;

import java.util.List;

public interface GenericDAO<T> {
    T save(T entity);
    List<T> getAll();
    T getById(int id);
    void update(T entity);
    void deleteById(int id);
}

