package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jtmcmoi.rental.repository.UserRepository;
import com.jtmcmoi.rental.repository.RentalRepository;
import com.jtmcmoi.rental.repository.MessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class TestController {

    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final MessageRepository messageRepository;

    public TestController(UserRepository userRepository, RentalRepository rentalRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/ping")
    public String getPing() {
        return "Pong";
    }

    @GetMapping("/db")
    public Map<String, Long> getDb() {
        return Map.of(
            "users", userRepository.count(),
            "rentals", rentalRepository.count(),
            "messages", messageRepository.count()
        );
    }
    
}
