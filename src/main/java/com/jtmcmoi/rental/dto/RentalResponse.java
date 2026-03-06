package com.jtmcmoi.rental.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RentalResponse(
    Integer id,
    String name,
    BigDecimal surface,
    BigDecimal price,
    String picture,
    String description,
    Integer ownerId,
    Instant createdAt,
    Instant updatedAt
) {}
