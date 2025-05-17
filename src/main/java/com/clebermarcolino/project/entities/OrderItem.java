package com.clebermarcolino.project.entities;

import com.clebermarcolino.project.entities.primarykey.OrderItemPK; // Importa a classe OrderItemPK, que representa a chave primária composta para esta entidade.
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId; // Importa a anotação JPA para indicar que um atributo é uma chave primária embutida.
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_order_item") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @EmbeddedId // Anotação JPA que indica que o atributo 'id' é uma chave primária embutida, referenciando a classe OrderItemPK.
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() { // Método para calcular o subtotal deste item do pedido (preço unitário multiplicado pela quantidade).
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
