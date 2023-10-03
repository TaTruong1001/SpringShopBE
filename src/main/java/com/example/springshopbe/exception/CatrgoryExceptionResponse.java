package com.example.springshopbe.exception;

import lombok.Data;
import org.springframework.core.SpringVersion;

@Data

public class CatrgoryExceptionResponse {
    private String message;

    public CatrgoryExceptionResponse(String message) {
        this.message = message;
    }
}
