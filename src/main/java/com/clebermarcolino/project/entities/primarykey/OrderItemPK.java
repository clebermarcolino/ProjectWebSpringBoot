package com.clebermarcolino.project.entities.primarykey;

import com.clebermarcolino.project.entities.Order;
import com.clebermarcolino.project.entities.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne // Anotação JPA que define um relacionamento Many-to-One com a entidade 'Order' (muitos itens de pedido para um pedido).
    @JoinColumn(name = "order_id") // Anotação JPA que especifica a coluna na tabela 'tb_order_item' que será usada como chave estrangeira para referenciar a tabela de 'Order'.
    private Order order;

    @ManyToOne // Anotação JPA que define um relacionamento Many-to-One com a entidade 'Product' (muitos itens de pedido para um produto).
    @JoinColumn(name = "product_id") // Anotação JPA que especifica a coluna na tabela 'tb_order_item' que será usada como chave estrangeira para referenciar a tabela de 'Product'.
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
