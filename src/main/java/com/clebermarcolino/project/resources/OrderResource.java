package com.clebermarcolino.project.resources;

import com.clebermarcolino.project.entities.Order;
import com.clebermarcolino.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Marca esta classe como um **controlador REST**, indicando que ela lida com requisições HTTP e retorna dados diretamente no corpo da resposta.
@RequestMapping(value = "/orders") // Mapeia todas as requisições que começam com "/orders" para os métodos desta classe.
public class OrderResource {

    @Autowired // Injeta uma instância de **OrderService**. O Spring se encarrega de encontrar e fornecer uma instância desse serviço.
    private OrderService orderService;

    @GetMapping // Mapeia requisições HTTP GET para o caminho base ("**/orders**").
    public ResponseEntity<List<Order>> findAll() { // Método para **encontrar todos os pedidos**.
        List<Order> orders = orderService.findAll(); // Chama o método `findAll()` do OrderService para obter uma lista de todos os pedidos.
        return ResponseEntity.ok().body(orders); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo a lista de pedidos.
    }

    @GetMapping(value = "/{id}") // Mapeia requisições HTTP GET para caminhos como "**/orders/{id}**", onde `{id}` é uma variável de caminho.
    public ResponseEntity<Order> findById(@PathVariable Long id) { // Método para **encontrar um pedido por ID**. `@PathVariable` vincula o `{id}` da URL ao parâmetro `id` do método.
        Order object = orderService.findById(id); // Chama o método `findById()` do OrderService para obter um pedido específico pelo ID.
        return ResponseEntity.ok().body(object); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo o objeto `Order` encontrado.
    }
}
