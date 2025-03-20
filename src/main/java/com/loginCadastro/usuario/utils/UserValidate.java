package com.loginCadastro.usuario.utils;

import com.loginCadastro.usuario.UserEnt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidate {
    private Pattern pattern;
    private Matcher matcher;

    private boolean ValidarEmail(String email){
        this.pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        this.matcher = pattern.matcher(email);

        return this.matcher.matches();
    }

    private boolean ValidarSenha(String senha){
        this.pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d).{9,}");
        this.matcher = pattern.matcher(senha);

        return this.matcher.matches();
    }

    public String ValidarCamposDeCadastro(UserEnt userEnt){
        if(userEnt.email().isEmpty() || userEnt.nome().isEmpty() || userEnt.senha().isEmpty()){
            return "nenhum campo deve estar vazio";
        }

        if(!this.ValidarEmail(userEnt.email())){
            return "Formato de email incorreto";
        }

        if(!this.ValidarSenha(userEnt.senha())){
            return "padrao de senha esta incorreto, senha deve conter 8 digitos um numero e uma cartactere";
        }

        return "";
    }
}
