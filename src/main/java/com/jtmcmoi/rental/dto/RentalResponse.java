package com.jtmcmoi.rental.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RentalResponse(
    Integer id,
    String name,
    BigDecimal surface,
    BigDecimal price,
    String picture,
    String description,
    @JsonProperty("owner_id") Integer ownerId,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("updated_at")String updatedAt
) {}
