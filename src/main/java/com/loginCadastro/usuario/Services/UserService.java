package com.loginCadastro.usuario.Services;

import com.loginCadastro.usuario.DTOs.Code;
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

import java.text.SimpleDateFormat;
import java.util.Date;
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
            ue.setTimeCode(new SimpleDateFormat("mm").format(new Date()));
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

    public boolean ValidarCode(Code code){

            if(code.email() == null || code.email().isEmpty()){
                throw new CodigoEmailInvalidoException("Campo de email nao pode estar vazio");
            }

            if(code.code() == 0){
                throw new CodigoEmailInvalidoException("Codigo esta vazio");
            }

            if(this.ur.existVerificado(code.email()).isEmpty()){
                throw new CodigoEmailInvalidoException("Enderco de email invalido, ou nao logado verifique tente novamente");
            }

            if(this.ur.existVerificado(code.email()).get()){
                throw new CodigoEmailInvalidoException("Usuario ja autenticado");
            }

            if(Integer.parseInt(this.ur.getTimeCodeByEmail(code.email())) >
                    Integer.parseInt(new SimpleDateFormat("mm").format(new Date())))
            {
                throw new CodigoEmailInvalidoException("codig expirado por favor tente novamente");
            }

        int DbCode = this.ur.findCodeByEmail(code.email());

        if(!(DbCode == code.code())){
            throw new CodigoEmailInvalidoException("Codigo invalido ou expirado verifique e tente novamente");
        }

        this.ur.setUsuarioVerificado(code.email());
        return true;
    }

}
