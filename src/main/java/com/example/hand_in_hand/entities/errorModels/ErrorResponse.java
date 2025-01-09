package com.example.hand_in_hand.entities.errorModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse { //hata mesajlarını döndürmek için oluşturulmuş model
    private String message;
    private int status;
    private long timestamp;
}
