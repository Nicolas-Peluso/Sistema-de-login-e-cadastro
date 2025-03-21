package com.loginCadastro.usuario.Infra;

import com.loginCadastro.usuario.ExceptionsC.CadastroException;
import com.loginCadastro.usuario.ExceptionsC.CodigoEmailInvalidoException;
import com.loginCadastro.usuario.utils.ResponseErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlerExceptions {

    @ExceptionHandler(CodigoEmailInvalidoException.class)
    public ResponseEntity<?> codigoDeEmailHandler(CodigoEmailInvalidoException ex){
        ResponseErro responseErro = new ResponseErro();
        responseErro.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseErro);
    }

    @ExceptionHandler(CadastroException.class)
    public ResponseEntity<?> CadastroErrorHandler(CadastroException ex){
        ResponseErro responseErro = new ResponseErro();
        responseErro.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseErro);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> RuntimeExceptionHandler(RuntimeException ex){
        ResponseErro responseErro = new ResponseErro();
        responseErro.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ExceptionHandler(Exception ex){
        ResponseErro responseErro = new ResponseErro();
        responseErro.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErro);
    }
}
