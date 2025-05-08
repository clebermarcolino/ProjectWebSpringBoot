package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.Category;
import com.clebermarcolino.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> object = categoryRepository.findById(id);
        return object.get();
    }
}
