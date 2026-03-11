package com.jtmcmoi.rental.service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jtmcmoi.rental.domain.User;
import com.jtmcmoi.rental.domain.Rental;
import com.jtmcmoi.rental.dto.RentalRequest;
import com.jtmcmoi.rental.dto.RentalResponse;
import com.jtmcmoi.rental.dto.RentalsResponse;
import com.jtmcmoi.rental.repository.RentalRepository;
import com.jtmcmoi.rental.repository.UserRepository;

@Service
public class RentalService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
        .ofPattern("yyyy/MM/dd")
        .withZone(ZoneId.systemDefault());

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final PictureStorageService pictureStorageService;

    public RentalService(
        RentalRepository rentalRepository,
        UserRepository userRepository,
        PictureStorageService pictureStorageService
    ) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.pictureStorageService = pictureStorageService;
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

    public void postRental(RentalRequest request, MultipartFile picture, String email) {

        User owner = this.userRepository.findByEmail(email)
            .orElseThrow();

        String url = this.pictureStorageService.storePicture(picture);

        Rental rental = new Rental();
        rental.setName(request.getName());
        rental.setSurface(request.getSurface());
        rental.setPrice(request.getPrice());
        rental.setDescription(request.getDescription());
        rental.setPicture(url);
        rental.setOwner(owner);

        this.rentalRepository.save(rental);
    }

    public void putRental(Integer id, RentalRequest request) {
        Rental rental = this.rentalRepository.findById(id)
            .orElseThrow();

        rental.setName(request.getName());
        rental.setSurface(request.getSurface());
        rental.setPrice(request.getPrice());
        rental.setDescription(request.getDescription());

        this.rentalRepository.save(rental);
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
            DATE_FORMAT.format(rental.getCreatedAt()),
            DATE_FORMAT.format(rental.getUpdatedAt())
        );
    }

}
