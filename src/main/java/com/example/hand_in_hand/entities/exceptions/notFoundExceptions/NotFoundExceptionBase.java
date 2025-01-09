package com.example.hand_in_hand.entities.exceptions.notFoundExceptions;

public class NotFoundExceptionBase extends RuntimeException implements NotFoundException {
    public NotFoundExceptionBase(String message) {
        super(message);
    }
    //veritabanında kayıt bulunamazsa hata fırlatılacak
    //her bir entity için ayrı ayrı hata sınıfları oluşturulacak
}
