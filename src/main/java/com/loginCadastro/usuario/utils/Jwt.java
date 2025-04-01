package com.loginCadastro.usuario.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class Jwt {
    
    public String generate(String email){
           return JWT.create()
           .withSubject(email)
           .withClaim("email", email)
           .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
           .sign(Algorithm.HMAC256("12344")
            );
    }
}
