package com.loginCadastro.usuario.Controller;

import com.loginCadastro.usuario.DTOs.Code;
import com.loginCadastro.usuario.Services.UserService;
import com.loginCadastro.usuario.UserEnt;
import com.loginCadastro.usuario.utils.ResponseCadastro;
import com.loginCadastro.usuario.utils.ResponseCode;
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
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseCadastro(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCadastro("usuario cadastrado com sucesso"));
    }

    @PostMapping("/verificar-email")
    public ResponseEntity<?> VerificarEmail(@RequestBody Code code){
        boolean usuarioValidado = userService.ValidarCode(code.code(), code.email());
        if(usuarioValidado){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseCode("Usuario Validado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseCode("Seu codigo esta expirado ou errado"));
    }
}
