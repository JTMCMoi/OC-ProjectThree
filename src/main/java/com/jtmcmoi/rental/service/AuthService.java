package com.jtmcmoi.rental.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jtmcmoi.rental.domain.User;
import com.jtmcmoi.rental.dto.AuthResponse;
import com.jtmcmoi.rental.dto.LoginRequest;
import com.jtmcmoi.rental.dto.RegisterRequest;
import com.jtmcmoi.rental.exception.InvalidCredentialsException;
import com.jtmcmoi.rental.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setName(request.name());

        userRepository.save(user);

        return new AuthResponse(tokenService.generateToken(user.getEmail()));

    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
            .orElseThrow(InvalidCredentialsException::new);

        if ( !passwordEncoder.matches(request.password(), user.getPassword()) ) {
            throw new InvalidCredentialsException();
        }

        return new AuthResponse(tokenService.generateToken(user.getEmail()));

    }

}
