package com.clebermarcolino.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_payment") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @Id // Anotação JPA que marca o campo 'id' como a chave primária desta entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação JPA que especifica a estratégia de geração de valores para a chave primária. GenerationType.IDENTITY indica que a geração será feita pela coluna de identidade do banco de dados (auto incremento).
    private Long id;
    private Instant moment;

    @OneToOne // Anotação JPA que define um relacionamento One-to-One com a entidade 'Order' (um pagamento para um pedido).
    @MapsId // Anotação JPA utilizada em relacionamentos One-to-One para indicar que a chave primária desta entidade ('Payment') é também a chave estrangeira referenciando a chave primária da entidade relacionada ('Order').
    @JsonIgnore // Anotação Jackson que indica que o atributo 'order' deve ser ignorado durante a serialização para JSON. Isso evita loops infinitos em relacionamentos bidirecionais.
    private Order order;

    public Payment() {
    }

    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
