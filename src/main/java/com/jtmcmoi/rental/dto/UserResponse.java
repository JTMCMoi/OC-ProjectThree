package com.jtmcmoi.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
    Integer id,
    String email,
    String name,
    @JsonProperty("created_at") String createdAt,
    @JsonProperty("updated_at")String updatedAt
) {}
