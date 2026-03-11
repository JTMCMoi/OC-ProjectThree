package com.jtmcmoi.rental.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalide credentials");
    }

}
