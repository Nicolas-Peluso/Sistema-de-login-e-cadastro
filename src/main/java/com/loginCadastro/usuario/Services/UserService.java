package com.loginCadastro.usuario.Services;

import com.loginCadastro.usuario.Entities.UserEntiti;
import com.loginCadastro.usuario.UserEnt;
import com.loginCadastro.usuario.UserRepository.UserRespository;
import com.loginCadastro.usuario.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRespository ur;

    public String Cadastro(UserEnt user){
        UserValidate validarCampos = new UserValidate();
        String valid = validarCampos.ValidarCamposDeCadastro(user);
        UserEntiti ue = new UserEntiti();

        if(!valid.isEmpty()){
            return valid;
        }

        ue.setNome(user.nome());
        ue.setEmail(user.email());
        ue.setSenha(user.senha());

        try{
            this.ur.save(ue);
        }catch (DataIntegrityViolationException ex){
            ex.printStackTrace();
            return "Erro";
        }

        return "";
    }
}
