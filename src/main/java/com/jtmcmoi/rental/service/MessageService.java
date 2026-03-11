package com.jtmcmoi.rental.service;

import org.springframework.stereotype.Service;
import com.jtmcmoi.rental.exception.BadRequestException;
import com.jtmcmoi.rental.domain.Message;
import com.jtmcmoi.rental.domain.Rental;
import com.jtmcmoi.rental.domain.User;
import com.jtmcmoi.rental.dto.MessageRequest;
import com.jtmcmoi.rental.repository.MessageRepository;
import com.jtmcmoi.rental.repository.RentalRepository;
import com.jtmcmoi.rental.repository.UserRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;


    public MessageService(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public void createMessage(MessageRequest request) {

        User user = this.userRepository.findById(request.user_id())
            .orElseThrow(() -> new BadRequestException());

        Rental rental = this.rentalRepository.findById(request.rental_id())
            .orElseThrow(() -> new BadRequestException());

        Message message = new Message();
        message.setMessage(request.message());
        message.setUser(user);
        message.setRental(rental);

        this.messageRepository.save(message);
    }

}
