package com.jtmcmoi.rental.dto;

import java.time.Instant;

public record UserResponse(
    Integer id,
    String email,
    String name,
    Instant created_at,
    Instant updated_at
) {}
