package com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations;

import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;

public class DemandNotFoundException extends NotFoundExceptionBase {
    public DemandNotFoundException(String message) {
        super(message);
    }
}
