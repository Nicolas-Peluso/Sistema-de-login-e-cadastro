package com.loginCadastro.usuario.UserRepository;

import com.loginCadastro.usuario.Entities.UserEntiti;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends CrudRepository<UserEntiti, Long> {

    @Query("SELECT code FROM UserEntiti WHERE email = :email")
    int findCodeByEmail(String email);

//    @Modifying
//    @Query("UPDATE UserEntiti SET usuario_verificado = 1 WHERE email = :email")
//    void SetUsuarioVerificado(String email);
}
