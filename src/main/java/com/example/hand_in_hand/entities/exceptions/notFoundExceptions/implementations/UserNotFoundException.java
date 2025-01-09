package com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations;

import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;

public class UserNotFoundException extends NotFoundExceptionBase {
    public UserNotFoundException(String message) {
        super(message);
    }
}
