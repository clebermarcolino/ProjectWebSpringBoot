package com.clebermarcolino.project.repositories;

import com.clebermarcolino.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
