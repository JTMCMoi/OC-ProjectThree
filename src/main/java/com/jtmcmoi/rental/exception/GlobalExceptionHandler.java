package com.jtmcmoi.rental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
        MethodArgumentNotValidException e,
        HttpServletRequest request
    ) {

        String path = request.getRequestURI();

        if ( "/api/auth/register".equals(request.getRequestURI()) ) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of());
        }

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .build();

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleOtherException(
        Exception e,
        HttpServletRequest request
    ) {

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .build();

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(
        Exception e,
        HttpServletRequest request
    ) {

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of());

    }

}
