package com.jtmcmoi.rental.service;

import org.springframework.stereotype.Service;
import com.jtmcmoi.rental.dto.UserResponse;
import com.jtmcmoi.rental.repository.UserRepository;
import com.jtmcmoi.rental.domain.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Integer id) {

        User user = this.userRepository.findById(id)
            .orElseThrow();

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );

    }

}
