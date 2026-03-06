package com.jtmcmoi.rental.service;

import org.springframework.stereotype.Service;

import com.jtmcmoi.rental.domain.Rental;
import com.jtmcmoi.rental.dto.RentalResponse;
import com.jtmcmoi.rental.dto.RentalsResponse;
import com.jtmcmoi.rental.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalsResponse getRentals() {
        return new RentalsResponse(
            this.rentalRepository.findAll().stream().map(this::toResponse).toList()
        );
    }

    public RentalResponse getRentalById(Integer id) {
        Rental rental = this.rentalRepository.findById(id)
            .orElseThrow();

        return this.toResponse(rental);
    }

    private RentalResponse toResponse(Rental rental) {
        return new RentalResponse(
            rental.getId(),
            rental.getName(),
            rental.getSurface(),
            rental.getPrice(),
            rental.getPicture(),
            rental.getDescription(),
            rental.getOwner().getId(),
            rental.getCreatedAt(),
            rental.getUpdatedAt()
        );
    }

}
