package com.clebermarcolino.project.entities;

import com.clebermarcolino.project.entities.enums.OrderStatus; // Importa a enumeração OrderStatus que define os possíveis status do pedido.
import com.fasterxml.jackson.annotation.JsonFormat; // Importa a anotação Jackson para formatar a saída de datas em JSON.
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant; // Importa a classe Instant da API de datas do Java 8 para representar um instante no tempo.
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_order") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class Order implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @Id // Anotação JPA que marca o campo 'id' como a chave primária desta entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação JPA que especifica a estratégia de geração de valores para a chave primária. GenerationType.IDENTITY indica que a geração será feita pela coluna de identidade do banco de dados (auto incremento).
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") // Anotação Jackson para formatar o atributo 'moment' como uma string no formato UTC
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne // Anotação JPA que define um relacionamento Many-to-One com a entidade 'User' (muitos pedidos para um cliente).
    @JoinColumn(name = "client_id") // Anotação JPA que especifica a coluna na tabela 'tb_order' que será usada como chave estrangeira para referenciar a tabela de 'User'.
    private User client;

    @OneToMany(mappedBy = "id.order") // Anotação JPA que define um relacionamento One-to-Many com a entidade 'OrderItem' (um pedido pode ter muitos itens). O atributo 'mappedBy' indica que o lado proprietário da relação está na classe 'OrderItem' e é gerenciado pelo atributo 'order' dentro da chave primária composta 'id'.
    private Set<OrderItem> items = new HashSet<OrderItem>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // Anotação JPA que define um relacionamento One-to-One com a entidade 'Payment' (um pedido tem um pagamento). O atributo 'mappedBy' indica que o lado proprietário da relação está na classe 'Payment' e é gerenciado pelo atributo 'order'. 'cascade = CascadeType.ALL' significa que todas as operações (persist, merge, remove, etc.) realizadas no 'Order' serão cascateadas para a entidade 'Payment'.
    private Payment payment;
    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public  Set<OrderItem> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal() { // Método para calcular o valor total do pedido, somando o subtotal de todos os itens.
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
