package com.loginCadastro.usuario.Controller;

import com.loginCadastro.usuario.utils.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginCadastro.usuario.Models.LoginDefault;
import com.loginCadastro.usuario.Models.LoginModel;

@RestController
@RequestMapping("login")
public class LoginControler {

    private AuthenticationManager authenticationManager;
    private Jwt jwt;

    public LoginControler(AuthenticationManager authenticationManager, Jwt jwt) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel){
        Authentication d = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(), loginModel.getSenha())
        );

        UserDetails user = (UserDetails) d.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginDefault(this.jwt.generate(user.getUsername())));
    }
}
