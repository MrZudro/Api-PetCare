package edu.sena.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenExpired extends RuntimeException {
    public TokenExpired(String message) {
        super(message);
    }   
}
