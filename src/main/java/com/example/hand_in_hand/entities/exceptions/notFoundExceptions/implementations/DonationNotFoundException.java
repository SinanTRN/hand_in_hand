package com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations;

import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;

public class DonationNotFoundException extends NotFoundExceptionBase {
    public DonationNotFoundException(String message) {
        super(message);
    }
}
