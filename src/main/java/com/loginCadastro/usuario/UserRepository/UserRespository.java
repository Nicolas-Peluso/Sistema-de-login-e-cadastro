package com.loginCadastro.usuario.UserRepository;

import com.loginCadastro.usuario.Entities.UserEntiti;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends CrudRepository<UserEntiti, Long> {

    @Query("SELECT code FROM UserEntiti WHERE email = :email")
    int findCodeByEmail(String email);

    @Modifying
    @Query("UPDATE UserEntiti SET usuarioVerificado = true WHERE email = :email")
    void setUsuarioVerificado(String email);

    @Query("SELECT usuarioVerificado FROM UserEntiti WHERE email = :email")
    boolean getVerificado(String email);
}
