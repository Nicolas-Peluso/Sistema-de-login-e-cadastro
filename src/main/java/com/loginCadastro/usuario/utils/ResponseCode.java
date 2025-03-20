package com.loginCadastro.usuario.utils;

public class ResponseCode {
    private String Message;

    public ResponseCode(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
