package com.clebermarcolino.project.resources;

import com.clebermarcolino.project.entities.Category;
import com.clebermarcolino.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Marca esta classe como um controlador REST, indicando que ela lida com requisições HTTP e retorna dados diretamente no corpo da resposta.
@RequestMapping(value = "/categories") // Mapeia todas as requisições que começam com "/categories" para os métodos desta classe.
public class CategoryResource {

    @Autowired // Injeta uma instância de CategoryService. O Spring automaticamente encontra e fornece uma instância do CategoryService.
    private CategoryService categoryService;

    @GetMapping // Mapeia requisições HTTP GET para o caminho base ("/categories").
    public ResponseEntity<List<Category>> findAll() { // Método para encontrar todas as categorias.
        List<Category> categories = categoryService.findAll(); // Chama o método findAll do CategoryService para obter uma lista de todas as categorias.
        return ResponseEntity.ok().body(categories); // Retorna uma resposta HTTP com status 200 OK e o corpo contendo a lista de categorias.
    }

    @GetMapping(value = "/{id}") // Mapeia requisições HTTP GET para caminhos como "/categories/{id}", onde {id} é uma variável de caminho.
    public ResponseEntity<Category> findById(@PathVariable Long id) { // Método para encontrar uma categoria por ID. @PathVariable vincula o {id} da URL ao parâmetro 'id' do método.
        Category object = categoryService.findById(id); // Chama o método findById do CategoryService para obter uma categoria específica pelo ID.
        return ResponseEntity.ok().body(object); // Retorna uma resposta HTTP com status 200 OK e o corpo contendo o objeto Category encontrado.
    }
}
