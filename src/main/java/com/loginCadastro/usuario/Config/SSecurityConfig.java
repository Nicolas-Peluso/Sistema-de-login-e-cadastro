package com.loginCadastro.usuario.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(
                autorizeConfig -> {
                    autorizeConfig.requestMatchers("/user").permitAll();
                    autorizeConfig.anyRequest().authenticated();
                }
        ).formLogin(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .build();
    }
}
