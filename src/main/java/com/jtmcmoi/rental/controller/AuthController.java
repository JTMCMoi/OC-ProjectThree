package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jtmcmoi.rental.dto.AuthResponse;
import com.jtmcmoi.rental.dto.LoginRequest;
import com.jtmcmoi.rental.dto.RegisterRequest;
import com.jtmcmoi.rental.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse postRegister(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public AuthResponse postLogin(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
