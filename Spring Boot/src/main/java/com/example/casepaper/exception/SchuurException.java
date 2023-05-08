package com.example.casepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SchuurException extends RuntimeException{
    public SchuurException(String message){
        super(message);
    }
}
