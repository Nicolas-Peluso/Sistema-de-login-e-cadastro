package com.loginCadastro.usuario.Controller;

import com.loginCadastro.usuario.DTOs.Code;
import com.loginCadastro.usuario.ExceptionsC.CadastroException;
import com.loginCadastro.usuario.Models.ResponseDefault;
import com.loginCadastro.usuario.Services.UserService;
import com.loginCadastro.usuario.UserEnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserControl {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> Cadastrar(@RequestBody UserEnt userEnt){
        String result = this.userService.Cadastro(userEnt);

        if(!result.isEmpty()){
            throw new CadastroException(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDefault("usuario cadastrado com sucesso"));
    }

    @PostMapping("/verificar-email")
    public ResponseEntity<?> VerificarEmail(@RequestBody Code code){
        userService.ValidarCode(code);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDefault("Usuario Validado"));
    }

}
