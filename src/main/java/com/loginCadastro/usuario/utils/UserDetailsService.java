package com.loginCadastro.usuario.utils;

import com.loginCadastro.usuario.UserRepository.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRespository userRespository;

    public UserDetailsService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var e = this.userRespository.getUserByEmail(username);
        if(e.isEmpty()){
            return (UserDetails) e.orElseThrow(() -> new UsernameNotFoundException("username not found"));
        }
        return new UserDetailsImpl(e.get().getEmail(), e.get().getSenha());
    }
}
