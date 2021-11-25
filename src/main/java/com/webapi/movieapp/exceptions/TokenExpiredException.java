package com.webapi.movieapp.exceptions;

public class TokenExpiredException extends Exception {

    public TokenExpiredException(String message) {
        super(message);
    }
}
