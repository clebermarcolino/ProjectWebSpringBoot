package com.clebermarcolino.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importa a anotação para ignorar logicamente a propriedade na serialização JSON.
import jakarta.persistence.*; // Importa as anotações JPA (Java Persistence API) para mapeamento objeto-relacional

import java.io.Serializable; // Importa a interface Serializable para permitir que objetos desta classe sejam convertidos em um fluxo de bytes.
import java.util.HashSet; // Importa a classe HashSet, uma implementação da interface Set que não permite elementos duplicados e não garante ordem
import java.util.Objects; // Importa a classe Objects, que contém métodos utilitários para trabalhar com objetos, incluindo equals e hashCode.
import java.util.Set;

@Entity // Anotação JPA que marca esta classe como uma entidade, representando uma tabela no banco de dados.
@Table(name = "tb_category") // Anotação JPA que especifica o nome da tabela no banco de dados que será mapeada para esta entidade.
public class Category implements Serializable {
    private static final long serialVersionUID = 1L; // Variável estática e final que armazena um identificador único para a classe serializada. É importante para controle de versão durante a desserialização.

    @Id // Anotação JPA que marca o campo 'id' como a chave primária desta entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação JPA que especifica a estratégia de geração de valores para a chave primária. GenerationType.IDENTITY indica que a geração será feita pela coluna de identidade do banco de dados (auto incremento).
    private Long id;
    private String name;

    @JsonIgnore // Anotação Jackson que indica que o campo 'products' deve ser ignorado durante a serialização para JSON. Isso evita loops infinitos em relacionamentos bidirecionais.
    @ManyToMany(mappedBy = "categories") // Anotação JPA que define um relacionamento Many-to-Many com a entidade 'Product'. O atributo 'mappedBy' indica que o lado proprietário da relação está na classe 'Product' e é gerenciado pelo atributo 'categories'.
    private Set<Product> products  = new HashSet<Product>();

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) { // Método sobrescrito para comparar se dois objetos da classe 'Category' são iguais. A igualdade é baseada no valor do atributo 'id'.
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() { // Método sobrescrito para gerar um código hash para o objeto 'Category'. É importante implementar 'hashCode' sempre que 'equals' é sobrescrito para garantir que objetos iguais tenham o mesmo código hash.
        return Objects.hashCode(id);
    }
}
