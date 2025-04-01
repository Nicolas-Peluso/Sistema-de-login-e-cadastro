package com.loginCadastro.usuario.Models;

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
