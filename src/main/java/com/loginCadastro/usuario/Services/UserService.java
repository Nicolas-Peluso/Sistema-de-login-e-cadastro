package com.loginCadastro.usuario.Services;

import com.loginCadastro.usuario.EmailEnt;
import com.loginCadastro.usuario.Entities.UserEntiti;
import com.loginCadastro.usuario.UserEnt;
import com.loginCadastro.usuario.UserRepository.UserRespository;
import com.loginCadastro.usuario.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRespository ur;

    @Autowired
    private final EmailService ems;

    public UserService(EmailService ems) {
        this.ems = ems;
    }

    public String Cadastro(UserEnt user){

        String valid = new UserValidate().ValidarCamposDeCadastro(user);
        UserEntiti ue = new UserEntiti();

        if(!valid.isEmpty()){
            return valid;
        }

        try{
            ue.setNome(user.nome());
            ue.setEmail(user.email());
            ue.setSenha(user.senha());
//            gerando um codigo entre 1000 e 9000
            Random r = new Random();
            ue.setCode(1000 + r.nextInt(9000));

            this.ur.save(ue);
        }catch (DataIntegrityViolationException ex){
            if(ex.getRootCause().getMessage().contains("Duplicate entry")){
                return "email ja cadastrado, voce pode fazer login";
            }
            return "Erro durante a operacao, tente novamente";
        }

        this.ems.EnviarEmail(new EmailEnt(user.email(), "Verificacao de email", "seu Codigo de verificacao e: " + ue.getCode()));

        return "";
    }

    public boolean ValidarCode(int code, String email){
        int DbCode = this.ur.findCodeByEmail(email);
        System.out.println(DbCode);
        if(DbCode == code){
//            this.ur.SetUsuarioVerificado(email);
            return true;
        }
        return false;
    }

}
