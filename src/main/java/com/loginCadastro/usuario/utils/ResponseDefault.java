package com.loginCadastro.usuario.utils;

public class ResponseDefault {
        private String Message;

    public ResponseDefault(String message) {
        Message = message;
    }

    public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }
    }
