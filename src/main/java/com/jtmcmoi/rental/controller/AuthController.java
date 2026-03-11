package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RestController;
import com.jtmcmoi.rental.dto.AuthResponse;
import com.jtmcmoi.rental.dto.LoginRequest;
import com.jtmcmoi.rental.dto.RegisterRequest;
import com.jtmcmoi.rental.dto.UserResponse;
import com.jtmcmoi.rental.service.AuthService;
import com.jtmcmoi.rental.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse postRegister(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public AuthResponse postLogin(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse getMethodName(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserByEmail(jwt.getSubject());
    }
    
}
