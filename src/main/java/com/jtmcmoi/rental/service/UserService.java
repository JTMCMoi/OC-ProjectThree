package com.jtmcmoi.rental.service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import com.jtmcmoi.rental.dto.UserResponse;
import com.jtmcmoi.rental.exception.InvalidCredentialsException;
import com.jtmcmoi.rental.repository.UserRepository;
import com.jtmcmoi.rental.domain.User;

@Service
public class UserService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
        .ofPattern("yyyy/MM/dd")
        .withZone(ZoneId.systemDefault());

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Integer id) {

        User user = this.userRepository.findById(id)
            .orElseThrow(InvalidCredentialsException::new);

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName(),
            DATE_FORMAT.format(user.getCreatedAt()),
            DATE_FORMAT.format(user.getUpdatedAt())
        );

    }

    public UserResponse getUserByEmail(String email) {

        User user = this.userRepository.findByEmail(email)
            .orElseThrow(InvalidCredentialsException::new);

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName(),
            DATE_FORMAT.format(user.getCreatedAt()),
            DATE_FORMAT.format(user.getUpdatedAt())
        );

    }

}
