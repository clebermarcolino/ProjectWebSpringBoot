package com.clebermarcolino.project.services;

import com.clebermarcolino.project.entities.User;
import com.clebermarcolino.project.repositories.UserRepository;
import com.clebermarcolino.project.services.exceptions.DataBaseException;
import com.clebermarcolino.project.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca esta classe como um **componente de serviço** do Spring. Isso indica que ela contém a lógica de negócios principal da aplicação e é gerenciada pelo contêiner Spring.
public class UserService {

    @Autowired // Injeta uma instância de **UserRepository**. O Spring se encarrega de encontrar e fornecer uma instância desse repositório, permitindo a comunicação com o banco de dados.
    private UserRepository userRepository;

    public List<User> findAll() { // Método para **encontrar todos os usuários**.
        return userRepository.findAll(); // Delega a chamada ao método `findAll()` do UserRepository, que busca e retorna uma lista de todos os usuários do banco de dados.
    }

    public User findById(Long id) { // Método para **encontrar um usuário por ID**.
        Optional<User> object = userRepository.findById(id); // Tenta encontrar um usuário pelo seu ID usando o UserRepository. O resultado é encapsulado em um `Optional`.
        return object.orElseThrow(() -> new ResourceNotFoundException(id)); // Se o `Optional` contiver um usuário, ele será retornado. Caso contrário (se o usuário não for encontrado), uma `ResourceNotFoundException` personalizada é lançada, indicando que o recurso com o ID especificado não existe.
    }

    public User insert(User object) { // Método para **inserir um novo usuário** no banco de dados.
        return userRepository.save(object); // Delega a operação de salvar (inserir) o objeto `User` ao UserRepository. O método `save()` do Spring Data JPA retorna a entidade persistida.
    }

    public void delete(Long id) { // Método para **excluir um usuário** pelo seu ID.
        try {
            userRepository.deleteById(id); // Tenta excluir o usuário pelo ID.
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id); // Lança uma `ResourceNotFoundException` personalizada, informando que o usuário a ser excluído não foi encontrado.
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage()); // Lança uma `DataBaseException` personalizada, encapsulando a mensagem de erro da exceção original do banco de dados.
        }
    }

    public User update(Long id, User object) { // Método para **atualizar um usuário existente**. Recebe o ID do usuário a ser atualizado e o objeto `User` com os dados atualizados.
        try {
            User entity = userRepository.getReferenceById(id); // Obtém uma referência (proxy) da entidade `User` do banco de dados sem realmente carregá-la completamente. Isso é útil para atualização, pois economiza uma consulta.
            updateData(entity, object); // Chama o método auxiliar `updateData` para copiar os dados atualizáveis do objeto recebido para a entidade persistida.
            return userRepository.save(entity); // Salva a entidade atualizada no banco de dados. Como a entidade já existe (foi obtida via `getReferenceById`), o `save()` realiza uma operação de atualização.
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id); // Lança uma `ResourceNotFoundException` personalizada, indicando que o usuário a ser atualizado não foi encontrado.
        }
    }

    private void updateData(User entity, User object) { // Método auxiliar privado para copiar dados de um objeto `User` para outro.
        entity.setName(object.getName()); // Atualiza o nome da entidade persistida com o nome do objeto recebido.
        entity.setEmail(object.getEmail()); // Atualiza o e-mail da entidade persistida com o e-mail do objeto recebido.
        entity.setPhone(object.getPhone()); // Atualiza o telefone da entidade persistida com o telefone do objeto recebido.
        // Observação: a senha não é atualizada aqui, pois geralmente é tratada em um método separado ou com criptografia.
    }
}
