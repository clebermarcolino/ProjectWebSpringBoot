---
# Projeto de Sistema de Pedidos (E-commerce Básico)

Este projeto é uma **API RESTful** desenvolvida em **Java** com **Spring Boot** que simula um sistema de pedidos e gerenciamento de produtos, categorias e usuários, comum em cenários de e-commerce. Ele serve como um exemplo prático de aplicação de conceitos de persistência de dados com JPA/Hibernate, injeção de dependência, e exposição de endpoints REST.

---
## Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java 17**: Linguagem de programação.
* **Postman**: Software para testar requisições HTTP.
* **Spring Boot 3.x**: Framework para construção rápida de aplicações Java, facilitando a configuração e o desenvolvimento de APIs RESTful.
* **Spring Data JPA**: Abstração para simplificar o acesso a dados, permitindo a criação de repositórios baseados em interfaces.
* **Hibernate**: Implementação da especificação JPA, responsável pelo mapeamento objeto-relacional.
* **H2 Database (modo em memória)**: Banco de dados relacional leve, ideal para desenvolvimento e testes, pois é executado em memória e os dados são perdidos ao reiniciar a aplicação.
* **Maven**: Ferramenta de gerenciamento de dependências e construção de projetos.
* **Jackson**: Biblioteca para serialização/desserialização de objetos Java para JSON e vice-versa.
* **RESTful Web Services**: Arquitetura para desenvolvimento de APIs que utilizam requisições HTTP para operações CRUD (Create, Read, Update, Delete).

---
## Estrutura do Projeto

O projeto segue uma estrutura de camadas típica de aplicações Spring:

* `entities`: Contém as classes que representam as entidades do banco de dados (`User`, `Product`, `Category`, `Order`, `OrderItem`, `Payment`).
* `entities.enums`: Contém a enumeração para o status do pedido (`OrderStatus`).
* `entities.primarykey`: Contém a classe para a chave primária composta de `OrderItem` (`OrderItemPK`).
* `repositories`: Interfaces que estendem `JpaRepository`, fornecendo métodos CRUD para cada entidade.
* `services`: Camada de lógica de negócios, onde as regras de negócio são implementadas e as operações de repositório são orquestradas. Também inclui classes de exceção personalizadas.
* `resources`: Camada de controladores REST, que expõe os endpoints da API e lida com as requisições HTTP.
* `resources.exceptions`: Classes para tratamento de erros padronizado na API.
* `config`: Classes de configuração do Spring, como a para popular o banco de dados de teste (`TestConfig`).

---
## Como Rodar o Projeto

Para executar este projeto em sua máquina local, siga os passos abaixo:

### Pré-requisitos

Certifique-se de ter o seguinte software instalado:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven 3.x ou superior**
* Uma IDE como **IntelliJ IDEA** ou **VS Code** (opcional, mas recomendado)

### Passos

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/CleberMarcolino/nomedoarquivo.git
    cd nomedoarquivo
    ```
    (Substitua `nomedoarquivo` pelo nome real do diretório do seu projeto, caso seja diferente.)

2.  **Construa o Projeto com Maven:**
    Navegue até o diretório raiz do projeto no seu terminal e execute o comando Maven para construir o projeto e baixar as dependências:
    ```bash
    mvn clean install
    ```

3.  **Execute a Aplicação:**
    Após a construção bem-sucedida, você pode executar a aplicação Spring Boot de algumas maneiras:

    * **Via Maven:**
        ```bash
        mvn spring-boot:run
        ```

    * **Executando o JAR:**
        Após o `mvn clean install`, um arquivo `.jar` será gerado no diretório `target/`. Você pode executá-lo diretamente:
        ```bash
        java -jar target/project-0.0.1-SNAPSHOT.jar # O nome do JAR pode variar ligeiramente
        ```

    * **Via IDE (IntelliJ IDEA, VS Code):**
        Abra o projeto em sua IDE preferida e execute a classe principal (aquela com a anotação `@SpringBootApplication`, geralmente `ProjectApplication.java`).

### Acessando a API

Por padrão, a aplicação será iniciada na porta **8080**. Como o banco de dados H2 está configurado para o perfil `test` e é em memória, os dados iniciais serão populados automaticamente quando a aplicação for iniciada (devido à classe `TestConfig`).

Você pode testar os endpoints da API usando ferramentas como **Postman**, **Insomnia** ou diretamente pelo navegador (para requisições GET):

* **Listar todos os usuários:** `GET http://localhost:8080/users`
* **Buscar usuário por ID:** `GET http://localhost:8080/users/1`
* **Listar todas as categorias:** `GET http://localhost:8080/categories`
* **Listar todos os produtos:** `GET http://localhost:8080/products`
* **Listar todos os pedidos:** `GET http://localhost:8080/orders`

---
