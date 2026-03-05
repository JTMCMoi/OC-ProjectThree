package com.jtmcmoi.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jtmcmoi.rental.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
