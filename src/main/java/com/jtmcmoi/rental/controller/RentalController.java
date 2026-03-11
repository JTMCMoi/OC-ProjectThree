package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.oauth2.jwt.Jwt;
import com.jtmcmoi.rental.dto.MessageResponse;
import com.jtmcmoi.rental.dto.RentalRequest;
import com.jtmcmoi.rental.dto.RentalResponse;
import com.jtmcmoi.rental.dto.RentalsResponse;
import com.jtmcmoi.rental.service.RentalService;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("")
    public RentalsResponse getRentals() {
        return this.rentalService.getRentals();
    }

    @GetMapping("/{id}")
    public RentalResponse getRental(@PathVariable Integer id) {
        return this.rentalService.getRentalById(id);
    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse postRental(
        @AuthenticationPrincipal Jwt jwt,
        @Valid @ModelAttribute RentalRequest request,
        @RequestPart("picture") MultipartFile picture
    ) {
        
        this.rentalService.postRental(request, picture, jwt.getSubject());
        return new MessageResponse("Rental created !");

    }
    
    @PutMapping("/{id}")
    public MessageResponse putRental(@PathVariable Integer id, @Valid @ModelAttribute RentalRequest request) {
        this.rentalService.putRental(id, request);        
        return new MessageResponse("Rental updated !");
    }
    
}
