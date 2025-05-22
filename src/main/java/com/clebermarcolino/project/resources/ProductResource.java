package com.clebermarcolino.project.resources;

import com.clebermarcolino.project.entities.Product;
import com.clebermarcolino.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Marca esta classe como um **controlador REST**, indicando que ela lida com requisições HTTP e retorna dados diretamente no corpo da resposta.
@RequestMapping(value = "/products") // Mapeia todas as requisições que começam com "/products" para os métodos desta classe.
public class ProductResource {

    @Autowired // Injeta uma instância de **ProductService**. O Spring se encarrega de encontrar e fornecer uma instância desse serviço.
    private ProductService productService;

    @GetMapping // Mapeia requisições HTTP GET para o caminho base ("**/products**")
    public ResponseEntity<List<Product>> findAll() { // Método para **encontrar todos os produtos**.
        List<Product> products = productService.findAll(); // Chama o método `findAll()` do ProductService para obter uma lista de todos os produtos.
        return ResponseEntity.ok().body(products); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo a lista de produtos.
    }

    @GetMapping(value = "/{id}") // Mapeia requisições HTTP GET para caminhos como "**/products/{id}**", onde `{id}` é uma variável de caminho.
    public ResponseEntity<Product> findById(@PathVariable Long id) { // Método para **encontrar um produto por ID**. `@PathVariable` vincula o `{id}` da URL ao parâmetro `id` do método.
        Product object = productService.findById(id); // Chama o método `findById()` do ProductService para obter um produto específico pelo ID.
        return ResponseEntity.ok().body(object); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo o objeto `Product` encontrado.
    }
}
