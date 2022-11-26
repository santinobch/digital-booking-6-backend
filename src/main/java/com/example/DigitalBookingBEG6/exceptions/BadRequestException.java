package com.example.DigitalBookingBEG6.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException{
    private String code;

    public BadRequestException(String code, String message) {
        super(message);
        this.code = code;
    }
}
