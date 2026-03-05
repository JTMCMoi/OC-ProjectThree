package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jtmcmoi.rental.dto.LoginRequest;
import com.jtmcmoi.rental.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String postMethodName(@Valid @RequestBody RegisterRequest request) {
        return "Register";
    }
    
    @PostMapping("/login")
    public String postMethodName(@Valid @RequestBody LoginRequest request) {
        return "Login";
    }
}
