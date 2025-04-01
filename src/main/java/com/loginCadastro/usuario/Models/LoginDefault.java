package com.loginCadastro.usuario.Models;

public class LoginDefault {
    private String token;

    public LoginDefault(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
