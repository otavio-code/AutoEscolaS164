package br.com.senai.autoescolas164.exception;

import br.com.senai.autoescolas164.exception.type.AlunoNotFoundException;
import br.com.senai.autoescolas164.exception.type.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarNotFount() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> tratarBadRequest(
            MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();
        return ResponseEntity
                .badRequest()
                .body(erros
                        .stream()
                        .map(DadosBadRequest::new)
                        .toList()
                );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Void> tratarAccessDenied() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<DadosException> tratarAlunoNotFound(AlunoNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DadosException(e.getMessage()));
    }

    //Falta o tratamento de InstrutorNotFoundException

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DadosException> tratarErrosValidacao(ValidacaoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new DadosException(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosException> tratarErroGenerico(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new DadosException(e.getMessage()));
    }

    private record DadosException(String erro) {
    }

    private record DadosBadRequest(String campo, String mensagem) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}