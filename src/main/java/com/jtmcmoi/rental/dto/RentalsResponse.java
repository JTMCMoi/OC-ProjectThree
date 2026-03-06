package com.jtmcmoi.rental.dto;

import java.util.List;

public record RentalsResponse(
    List<RentalResponse> rentals
) {}
