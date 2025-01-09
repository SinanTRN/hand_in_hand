package com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations;

import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;

public class LocationNotFoundException extends NotFoundExceptionBase {
    public LocationNotFoundException(String message) {
        super(message);
    }
}
