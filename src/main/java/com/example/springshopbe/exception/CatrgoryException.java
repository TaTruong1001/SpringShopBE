package com.example.springshopbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class CatrgoryException extends RuntimeException{
    public CatrgoryException(String message) {
        super(message);
    }
}
