package com.jtmcmoi.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jtmcmoi.rental.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
