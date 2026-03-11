package com.jtmcmoi.rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class RentalRequest {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal surface;

    @NotNull
    private BigDecimal price;

    @NotBlank
    @Size(max = 2000)
    private String description;

    public String getName() { return this.name; }
    public BigDecimal getSurface() { return this.surface; }
    public BigDecimal getPrice() { return this.price; }
    public String getDescription() { return this.description; }

    public void setName(String name) { this.name = name; }
    public void setSurface(BigDecimal surface) { this.surface = surface; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }

}
