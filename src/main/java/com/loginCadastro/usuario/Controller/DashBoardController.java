package com.loginCadastro.usuario.Controller;

import com.loginCadastro.usuario.utils.ResponseDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {

    @GetMapping
    public ResponseEntity<?> getPreferencias(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDefault("VOCE ESTA AUTENTICADO"));
    }
}
