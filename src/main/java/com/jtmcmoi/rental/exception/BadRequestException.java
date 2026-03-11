package com.jtmcmoi.rental.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Invalide credentials");
    }
}
