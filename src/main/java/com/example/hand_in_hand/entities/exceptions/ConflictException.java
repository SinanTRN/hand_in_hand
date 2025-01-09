package com.example.hand_in_hand.entities.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
    //veritabanında aynı kayıt varsa hata fırlatılacak
}
