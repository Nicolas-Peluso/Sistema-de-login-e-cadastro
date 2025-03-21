package com.loginCadastro.usuario.Services;

import com.loginCadastro.usuario.EmailEnt;
import com.loginCadastro.usuario.Entities.UserEntiti;
import com.loginCadastro.usuario.ExceptionsC.CadastroException;
import com.loginCadastro.usuario.ExceptionsC.CodigoEmailInvalidoException;
import com.loginCadastro.usuario.UserEnt;
import com.loginCadastro.usuario.UserRepository.UserRespository;
import com.loginCadastro.usuario.utils.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            throw new CadastroException(valid);
        }

        try{
            ue.setNome(user.nome());
            ue.setEmail(user.email());
            ue.setSenha(new BCryptPasswordEncoder().encode(user.senha()));
            ue.setUsuarioVerificado(false);

            Random r = new Random();
            ue.setCode(1000 + r.nextInt(9000));

            this.ur.save(ue);
        }catch (DataIntegrityViolationException ex){
            if(ex.getRootCause().getMessage().contains("Duplicate entry")){
                return "email ja cadastrado, voce pode fazer login";
            }
        }

        this.ems.EnviarEmail(new EmailEnt(user.email(), "Verificacao de email", "seu Codigo de verificacao e: " + ue.getCode()));

        return "";
    }

    public boolean ValidarCode(int code, String email){

            if(this.ur.getVerificado(email) != true && this.ur.getVerificado(email) != false){
                throw new CodigoEmailInvalidoException("Enderco de email invalido");
            }

            if(this.ur.getVerificado(email)){
                throw new CodigoEmailInvalidoException("Usuario ja autenticado");
            }

        int DbCode = this.ur.findCodeByEmail(email);

        if(DbCode == code){
            this.ur.setUsuarioVerificado(email);
            return true;
        }
        return false;
    }

}
