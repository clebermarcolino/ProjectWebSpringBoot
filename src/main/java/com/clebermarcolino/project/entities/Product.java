package com.clebermarcolino.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet; // Importa a classe HashSet, uma implementação da interface Set que não permite elementos duplicados e não garante ordem.
import java.util.Objects;
import java.util.Set; // Importa a interface Set, uma coleção que não contém elementos duplicados.

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_product") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @Id // Anotação JPA que marca o campo 'id' como a chave primária desta entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação JPA que especifica a estratégia de geração de valores para a chave primária. GenerationType.IDENTITY indica que a geração será feita pela coluna de identidade do banco de dados (auto incremento).
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany // Anotação JPA que define um relacionamento Many-to-Many com a entidade 'Category' (um produto pode pertencer a várias categorias e uma categoria pode ter vários produtos).
    @JoinTable(name = "tb_product_category", // Anotação JPA que especifica a tabela de junção para o relacionamento Many-to-Many.
            joinColumns = @JoinColumn(name = "product_id"), // Especifica a coluna na tabela de junção que armazena a chave estrangeira para a tabela de 'Product'.
            inverseJoinColumns = @JoinColumn(name = "category_id")) // Especifica a coluna na tabela de junção que armazena a chave estrangeira para a tabela de 'Category'.
    private Set<Category> categories = new HashSet<Category>();

    @OneToMany(mappedBy = "id.product") // Anotação JPA que define um relacionamento One-to-Many com a entidade 'OrderItem' (um produto pode estar em vários itens de pedido). O atributo 'mappedBy' indica que o lado proprietário da relação está na classe 'OrderItem' e é gerenciado pelo atributo 'product' dentro da chave primária composta 'id'.
    private Set<OrderItem> items = new HashSet<OrderItem>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore // Anotação Jackson que indica que o método 'getOrders()' deve ser ignorado durante a serialização para JSON. Isso evita loops infinitos em relacionamentos bidirecionais.
    public Set<Order> getOrders() { // Método para obter o conjunto de pedidos nos quais este produto está presente.
        Set<Order> set = new HashSet<Order>(); // Cria um novo HashSet para armazenar os pedidos.
        for (OrderItem x : items) { // Itera sobre cada 'OrderItem' no conjunto 'items' (itens de pedido que contêm este produto).
            set.add(x.getOrder()); // Adiciona o pedido associado a cada 'OrderItem' ao conjunto de pedidos.
        }
        return set; // Retorna o conjunto de pedidos nos quais este produto foi incluído.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
