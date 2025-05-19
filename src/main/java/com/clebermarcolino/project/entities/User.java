package com.clebermarcolino.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_user") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @Id // Anotação JPA que marca o campo 'id' como a chave primária desta entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação JPA que especifica a estratégia de geração de valores para a chave primária. GenerationType.IDENTITY indica que a geração será feita pela coluna de identidade do banco de dados (auto incremento).
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore // Anotação Jackson que indica que o atributo 'orders' deve ser ignorado durante a serialização para JSON. Isso evita loops infinitos em relacionamentos bidirecionais.
    @OneToMany(mappedBy = "client") // Anotação JPA que define um relacionamento One-to-Many com a entidade 'Order' (um usuário pode ter vários pedidos). O atributo 'mappedBy' indica que o lado proprietário da relação está na classe 'Order' e é gerenciado pelo atributo 'client'.
    private List<Order> orders = new ArrayList<Order>();

    public User() {

    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
