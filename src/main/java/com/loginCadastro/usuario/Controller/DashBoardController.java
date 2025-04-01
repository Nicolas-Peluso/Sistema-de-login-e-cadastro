package com.loginCadastro.usuario.Controller;

import com.loginCadastro.usuario.utils.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginCadastro.usuario.Models.ResponseDefault;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {
    private Jwt jwt;

    public DashBoardController(Jwt jwt) {
        this.jwt = jwt;
    }

    @GetMapping
    public ResponseEntity<?> getPreferencias(@RequestHeader("Authorization") String token){

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDefault("sss"));
    }
}
