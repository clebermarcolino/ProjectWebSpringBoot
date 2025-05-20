package com.clebermarcolino.project.resources.exceptions;

import com.clebermarcolino.project.services.exceptions.DataBaseException;
import com.clebermarcolino.project.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // Anotação do Spring que permite que esta classe trate exceções lançadas por qualquer Controller na aplicação. Ela é uma "advise" global.
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // Anotação que mapeia o método 'resourceNotFound' para tratar especificamente exceções do tipo ResourceNotFoundException.
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND; // Define o status HTTP como 404 Not Found.
        // Cria uma nova instância de StandardError com as informações da exceção.
        StandardError err = new StandardError(Instant.now(), // Momento atual da exceção.
                httpStatus.value(), // Código numérico do status HTTP (404).
                error, // Mensagem de erro genérica.
                e.getMessage(), // Mensagem específica da exceção (obtida da ResourceNotFoundException).
                request.getRequestURI()); // URI da requisição que causou a exceção.
        // Retorna um ResponseEntity com o status HTTP definido e o corpo contendo o objeto StandardError.
        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(DataBaseException.class) // Anotação que mapeia o método 'dataBase' para tratar especificamente exceções do tipo DataBaseException.
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // Define o status HTTP como 400 Bad Request (comumente usado para erros de requisição que podem ser causados por problemas no DB).
        // Cria uma nova instância de StandardError com as informações da exceção.
        StandardError err = new StandardError(Instant.now(), // Momento atual da exceção.
                httpStatus.value(), // Código numérico do status HTTP (400).
                error, // Mensagem de erro genérica.
                e.getMessage(), // Mensagem específica da exceção (obtida da DataBaseException).
                request.getRequestURI()); // URI da requisição que causou a exceção.
        // Retorna um ResponseEntity com o status HTTP definido e o corpo contendo o objeto StandardError.
        return ResponseEntity.status(httpStatus).body(err);
    }
}
