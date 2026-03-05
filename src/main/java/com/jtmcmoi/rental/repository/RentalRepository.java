package com.jtmcmoi.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jtmcmoi.rental.domain.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
