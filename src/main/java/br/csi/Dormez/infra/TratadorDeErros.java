package br.csi.Dormez.infra;

import br.csi.Dormez.infra.exceptions.RecursoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }*/

    // 400 - Erros de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErrosDadosInvalidos(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        List<DadosErroValidacao> dados = new ArrayList<>();
        for (FieldError fe : errors) {
            dados.add(new DadosErroValidacao(fe.getField(), fe.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(dados);
    }

    // 404 - Recurso não encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity tratarErroRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(new DadosErro(ex.getMessage()));
    }

    // 500 - Erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErroInesperado(Exception ex) {
        return ResponseEntity.status(500)
                .body(new DadosErro("Ocorreu um erro inesperado."));
    }


    private record DadosErroValidacao(String campo, String mensagem) {}

    private record DadosErro(String mensagem) {}

    public record MensagemSucesso(String mensagem) {}
}
