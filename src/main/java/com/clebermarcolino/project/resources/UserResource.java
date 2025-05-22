package com.clebermarcolino.project.resources;

import com.clebermarcolino.project.entities.User;
import com.clebermarcolino.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // Marca esta classe como um **controlador REST**, indicando que ela lida com requisições HTTP e retorna dados diretamente no corpo da resposta.
@RequestMapping(value = "/users") // Mapeia todas as requisições que começam com "/users" para os métodos desta classe.
public class UserResource {

    @Autowired // Injeta uma instância de **UserService**. O Spring se encarrega de encontrar e fornecer uma instância desse serviço.
    private UserService userService;

    @GetMapping // Mapeia requisições HTTP GET para o caminho base ("**/users**").
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}") // Mapeia requisições HTTP GET para caminhos como "**/users/{id}**", onde `{id}` é uma variável de caminho.
    public ResponseEntity<User> findById(@PathVariable Long id) { // Método para **encontrar um usuário por ID**. `@PathVariable` vincula o `{id}` da URL ao parâmetro `id` do método.
        User object = userService.findById(id); // Chama o método `findById()` do UserService para obter um usuário específico pelo ID
        return ResponseEntity.ok().body(object); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo o objeto `User` encontrado.
    }

    @PostMapping // Mapeia requisições HTTP POST para o caminho base ("**/users**"). Usado para **inserir novos usuários**.
    public ResponseEntity<User> insert(@RequestBody User object) { // Método para **inserir um novo usuário**. `@RequestBody` indica que o corpo da requisição HTTP deve ser mapeado para o objeto `User`.
        object = userService.insert(object); // Chama o método `insert()` do UserService para salvar o novo usuário no banco de dados. O objeto `User` retornado pode ter o ID gerado.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri(); // Constrói a URI para o recurso recém-criado. Isso é uma boa prática RESTful para informar o cliente onde o novo recurso pode ser acessado.
        return ResponseEntity.created(uri).body(object); // Retorna uma **resposta HTTP com status 201 Created**, o cabeçalho `Location` contendo a URI do novo recurso e o corpo contendo o objeto `User` criado.
    }

    @DeleteMapping(value = "/{id}") // Mapeia requisições HTTP DELETE para caminhos como "**/users/{id}**". Usado para **deletar um usuário**.
    public ResponseEntity<Void> delete(@PathVariable Long id) { // Método para **deletar um usuário por ID**.
        userService.delete(id); // Chama o método `delete()` do UserService para remover o usuário do banco de dados.
        return ResponseEntity.noContent().build(); // Retorna uma **resposta HTTP com status 204 No Content**, indicando que a requisição foi bem-sucedida, mas não há conteúdo para retornar.
    }

    @PutMapping // Mapeia requisições HTTP PUT para caminhos como "**/users/{id}**". Usado para **atualizar um usuário existente**.
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User object) { // Método para **atualizar um usuário**. `@PathVariable` para o ID e `@RequestBody` para os dados atualizados.
        object = userService.update(id, object); // Chama o método `update()` do UserService para atualizar o usuário com o ID fornecido e os novos dados.
        return ResponseEntity.ok().body(object); // Retorna uma **resposta HTTP com status 200 OK** e o corpo contendo o objeto `User` atualizado.
    }
}
