package com.jtmcmoi.rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageRequest(
    @NotBlank String message,
    @NotNull Integer user_id,
    @NotNull Integer rental_id
) {}
