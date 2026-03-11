package com.jtmcmoi.rental.dto;

public record UserResponse(
    Integer id,
    String email,
    String name,
    String created_at,
    String updated_at
) {}
