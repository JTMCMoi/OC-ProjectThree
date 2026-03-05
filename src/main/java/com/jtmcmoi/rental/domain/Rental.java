package com.jtmcmoi.rental.domain;

import java.math.BigDecimal;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "surface")
    private BigDecimal surface;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "picture", length = 255)
    private String picture;

    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Integer getId() { return this.id; }
    public String getName() { return this.name; }
    public BigDecimal getSurface() { return this.surface; }
    public BigDecimal getPrice() { return this.price; }
    public String getPicture() { return this.picture; }
    public String getDescription() { return this.description; }
    public User getOwner() { return this.owner; }
    public Instant getCreatedAt() { return this.createdAt; }
    public Instant getUpdatedAt() { return this.updatedAt; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurface(BigDecimal surface) { this.surface = surface; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setDescription(String description) { this.description = description; }
    public void setOwner(User owner) { this.owner = owner; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

}
