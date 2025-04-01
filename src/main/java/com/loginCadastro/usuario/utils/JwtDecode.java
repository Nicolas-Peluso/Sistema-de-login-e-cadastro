package com.loginCadastro.usuario.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtDecode {

    public DecodedJWT decodedJWT(String token){
        return JWT.require(Algorithm.HMAC256("12344"))
                .build()
                .verify(token);
    }
}
