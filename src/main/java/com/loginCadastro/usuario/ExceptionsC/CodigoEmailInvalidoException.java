package com.loginCadastro.usuario.ExceptionsC;

public class CodigoEmailInvalidoException extends RuntimeException {

    public CodigoEmailInvalidoException() {
        super("Codigo Invalido");
    }

    public CodigoEmailInvalidoException(String m){
            super(m);
    }
}
