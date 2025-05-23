package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.Order;
import com.clebermarcolino.project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca esta classe como um **componente de serviço** do Spring. Isso indica que ela contém a lógica de negócios e é gerenciada pelo contêiner Spring.
public class OrderService {

    @Autowired // Injeta uma instância de **OrderRepository**. O Spring se encarrega de encontrar e fornecer uma instância desse repositório.
    private OrderRepository orderRepository;

    public List<Order> findAll() { // Método para **encontrar todos os pedidos**.
        return orderRepository.findAll();
    }

    public Order findById(Long id) { // Método para **encontrar um pedido por ID**.
        Optional<Order> object = orderRepository.findById(id);
        return object.get();
    }
}
