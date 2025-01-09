package com.example.hand_in_hand.entities.exceptions.handler;

import com.example.hand_in_hand.entities.exceptions.AuthorizationException;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.NotFoundExceptionBase;
import com.example.hand_in_hand.entities.errorModels.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // tüm controller sınıflarını kapsar. controller sınıflarında oluşan hataları yakalar
public class GlobalExceptionHandler { // hataları global olarak ele almak için oluşturulan sınıf
    // Exception handling AuthenticationException
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NotFoundExceptionBase.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNotFoundException(NotFoundExceptionBase e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),//hata mesajı
                HttpStatus.NOT_FOUND.value(),//hata kodu
                System.currentTimeMillis());//hata zamanı
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);//oluşan hatayı döndürür
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleConflictException(ConflictException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
