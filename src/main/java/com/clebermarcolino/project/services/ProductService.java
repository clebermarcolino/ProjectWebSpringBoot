package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.Product;
import com.clebermarcolino.project.entities.User;
import com.clebermarcolino.project.repositories.ProductRepository;
import com.clebermarcolino.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> object = productRepository.findById(id);
        return object.get();
    }
}
