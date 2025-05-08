package com.clebermarcolino.project.repositories;

import com.clebermarcolino.project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
