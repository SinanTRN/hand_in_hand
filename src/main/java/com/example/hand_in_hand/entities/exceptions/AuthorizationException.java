package com.example.hand_in_hand.entities.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
    //kullanıcı yetkisiz işlem yapmaya çalışırsa hata fırlatılacak
}
