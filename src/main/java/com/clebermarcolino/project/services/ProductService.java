package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.Product;
import com.clebermarcolino.project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca esta classe como um **componente de serviço** do Spring. Isso indica que ela contém a lógica de negócios e é gerenciada pelo contêiner Spring.
public class ProductService {

    @Autowired // Injeta uma instância de **ProductRepository**. O Spring se encarrega de encontrar e fornecer uma instância desse repositório.
    private ProductRepository productRepository;

    public List<Product> findAll() { // Método para **encontrar todos os produtos**.
        return productRepository.findAll(); // Delega a chamada ao método `findAll()` do ProductRepository, que busca todos os produtos no banco de dados.
    }

    public Product findById(Long id) { // Método para **encontrar um produto por ID**.
        Optional<Product> object = productRepository.findById(id); // Chama o método `findById()` do ProductRepository para tentar encontrar um produto pelo seu ID. Retorna um `Optional` para lidar com a ausência do objeto.
        return object.get(); // Retorna o objeto `Product` contido no `Optional`. **Importante:** Usar `.get()` diretamente pode lançar uma `NoSuchElementException` se o produto com o `id` especificado não for encontrado. Em aplicações robustas, é recomendado usar `orElseThrow()` com uma exceção personalizada ou `orElse(null)` para um tratamento mais seguro da ausência de dados.
    }
}
