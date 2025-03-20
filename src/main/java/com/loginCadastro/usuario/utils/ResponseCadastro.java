package com.loginCadastro.usuario.utils;

public class ResponseCadastro {
    private String menssagem;

    public ResponseCadastro(String menssagem) {
        this.menssagem = menssagem;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
