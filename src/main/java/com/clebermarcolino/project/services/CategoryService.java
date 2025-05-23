package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.Category;
import com.clebermarcolino.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca esta classe como um **componente de serviço** do Spring. Isso indica que ela contém a lógica de negócios e é gerenciada pelo contêiner Spring.
public class CategoryService {

    @Autowired // Injeta uma instância de **CategoryRepository**. O Spring se encarrega de encontrar e fornecer uma instância desse repositório.
    private CategoryRepository categoryRepository;

    public List<Category> findAll() { // Método para **encontrar todas as categorias**.
        return categoryRepository.findAll(); // Delega a chamada ao método `findAll()` do CategoryRepository, que busca todas as categorias no banco de dados.
    }

    public Category findById(Long id) { // Método para **encontrar uma categoria por ID**.
        Optional<Category> object = categoryRepository.findById(id); // Chama o método `findById()` do CategoryRepository para tentar encontrar uma categoria pelo seu ID. Retorna um `Optional` para lidar com a ausência do objeto.
        return object.get(); // Retorna o objeto `Category` contido no `Optional`. **Cuidado:** `get()` deve ser usado apenas quando você tem certeza de que o `Optional` contém um valor. Em um sistema de produção, você usaria `orElseThrow()` para lançar uma exceção se o objeto não for encontrado.
    }
}
