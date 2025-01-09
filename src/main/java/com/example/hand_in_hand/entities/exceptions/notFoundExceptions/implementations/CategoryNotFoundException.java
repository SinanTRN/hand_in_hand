package com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations;

import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;

public class CategoryNotFoundException extends NotFoundExceptionBase {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
