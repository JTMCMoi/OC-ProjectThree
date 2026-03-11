package com.jtmcmoi.rental.dto;

import java.math.BigDecimal;

public record RentalResponse(
    Integer id,
    String name,
    BigDecimal surface,
    BigDecimal price,
    String picture,
    String description,
    Integer ownerId,
    String createdAt,
    String updatedAt
) {}
