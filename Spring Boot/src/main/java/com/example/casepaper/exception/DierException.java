package com.example.casepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DierException extends RuntimeException{
    public DierException(String message){
        super(message);
    }
}
