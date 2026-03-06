package com.jtmcmoi.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jtmcmoi.rental.dto.RentalResponse;
import com.jtmcmoi.rental.dto.RentalsResponse;
import com.jtmcmoi.rental.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    
    
}
