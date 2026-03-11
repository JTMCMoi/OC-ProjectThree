package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jtmcmoi.rental.dto.MessageRequest;
import com.jtmcmoi.rental.dto.MessageResponse;
import com.jtmcmoi.rental.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    final private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("")
    public MessageResponse postMessage(@Valid @RequestBody MessageRequest request) {
        this.messageService.createMessage(request);
        return new MessageResponse("Message send with success");
    }
    
}
