package com.loginCadastro.usuario.UserRepository;

import com.loginCadastro.usuario.Entities.UserEntiti;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends CrudRepository<UserEntiti, Long> { }
