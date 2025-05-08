package com.clebermarcolino.project.repositories;

import com.clebermarcolino.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
